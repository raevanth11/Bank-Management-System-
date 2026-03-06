import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const API_URL = 'http://localhost:8080/api/accounts';

function App() {
  const [accounts, setAccounts] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState(null);
  const [view, setView] = useState('list');
  const [formData, setFormData] = useState({ holderName: '', initialDeposit: '' });
  const [showModal, setShowModal] = useState(false);
  const [modalType, setModalType] = useState('');
  const [modalAmount, setModalAmount] = useState('');

  useEffect(() => {
    fetchAccounts();
  }, []);

  const fetchAccounts = async () => {
    try {
      const response = await axios.get(API_URL);
      setAccounts(response.data);
    } catch (error) {
      console.error('Error fetching accounts:', error);
      if (error.code === 'ERR_NETWORK') {
        console.log('Backend server is not running. Please start it with: cd backend && mvn spring-boot:run');
      }
    }
  };

  const createAccount = async (e) => {
    e.preventDefault();
    if (!formData.holderName || !formData.initialDeposit) {
      return;
    }
    try {
      const response = await axios.post(API_URL, {
        holderName: formData.holderName,
        initialDeposit: parseFloat(formData.initialDeposit)
      });
      console.log('Account created:', response.data);
      setFormData({ holderName: '', initialDeposit: '' });
      fetchAccounts();
      setView('list');
    } catch (error) {
      console.error('Error creating account:', error);
      if (error.response) {
        alert(`Error: ${error.response.data.error || error.response.data.message || 'Failed to create account'}`);
      } else if (error.request) {
        alert('Cannot connect to server. Make sure the backend is running on http://localhost:8080');
      } else {
        alert('Error creating account: ' + error.message);
      }
    }
  };

  const openModal = (type) => {
    setModalType(type);
    setModalAmount('');
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setModalType('');
    setModalAmount('');
  };

  const handleTransaction = async () => {
    if (!selectedAccount || !modalAmount) {
      return;
    }
    try {
      await axios.post(`${API_URL}/${selectedAccount.accountNumber}/${modalType}`, {
        amount: parseFloat(modalAmount)
      });
      closeModal();
      fetchAccounts();
      const updated = await axios.get(`${API_URL}/${selectedAccount.accountNumber}`);
      setSelectedAccount(updated.data);
    } catch (error) {
      const errorMsg = error.response?.data?.error || `Error processing ${modalType}`;
      alert(errorMsg);
    }
  };

  const closeAccount = async () => {
    if (!selectedAccount) return;
    
    const confirmClose = window.confirm(
      `Are you sure you want to close account #${selectedAccount.accountNumber} (${selectedAccount.holderName})?\n\nThis action cannot be undone.`
    );
    
    if (!confirmClose) return;

    try {
      await axios.delete(`${API_URL}/${selectedAccount.accountNumber}`);
      setView('list');
      setSelectedAccount(null);
      fetchAccounts();
    } catch (error) {
      alert('Error closing account: ' + (error.response?.data?.error || error.message));
    }
  };

  const getModalTitle = () => {
    switch(modalType) {
      case 'deposit': return '💵 Deposit Money';
      case 'withdraw': return '💸 Withdraw Money';
      case 'loan': return '🏦 Apply for Loan';
      case 'repay': return '💳 Repay Loan';
      default: return '';
    }
  };

  return (
    <div className="App">
      <header className="header">
        <h1>🏦 Bank Management System</h1>
        <p>Secure • Fast • Reliable</p>
      </header>

      <div className="container">
        <nav className="nav">
          <button onClick={() => setView('list')} className={view === 'list' ? 'active' : ''}>📊 All Accounts</button>
          <button onClick={() => setView('create')} className={view === 'create' ? 'active' : ''}>➕ Create Account</button>
        </nav>

        {view === 'list' && (
          accounts.length === 0 ? (
            <div className="empty-state">
              <h3>No Accounts Yet</h3>
              <p>Create your first account to get started!</p>
              <button onClick={() => setView('create')}>Create Account</button>
            </div>
          ) : (
            <div className="accounts-grid">
              {accounts.map(account => (
                <div key={account.accountNumber} className="account-card" onClick={() => { setSelectedAccount(account); setView('details'); }}>
                  <h3>👤 {account.holderName}</h3>
                  <p>Account #: {account.accountNumber}</p>
                  <p className="balance">💰 Balance: ₹{account.balance.toFixed(2)}</p>
                  {account.loanAmount > 0 && <p className="loan">🏦 Loan: ₹{account.loanAmount.toFixed(2)}</p>}
                </div>
              ))}
            </div>
          )
        )}

        {view === 'create' && (
          <form onSubmit={createAccount} className="form">
            <h2>Create New Account</h2>
            <input type="text" placeholder="👤 Account Holder Name" value={formData.holderName} onChange={(e) => setFormData({ ...formData, holderName: e.target.value })} required />
            <input type="number" placeholder="💵 Initial Deposit" value={formData.initialDeposit} onChange={(e) => setFormData({ ...formData, initialDeposit: e.target.value })} required />
            <button type="submit">Create Account</button>
          </form>
        )}

        {view === 'details' && selectedAccount && (
          <div className="details">
            <div className="details-header">
              <button onClick={() => setView('list')} className="back-btn">← Back to Accounts</button>
              <button onClick={closeAccount} className="close-account-btn">🗑️ Close Account</button>
            </div>
            <div className="account-info">
              <h2>👤 {selectedAccount.holderName}</h2>
              <p>Account Number: {selectedAccount.accountNumber}</p>
              <p className="balance-large">💰 Balance: ₹{selectedAccount.balance.toFixed(2)}</p>
              <p className="loan-large">🏦 Loan: ₹{selectedAccount.loanAmount.toFixed(2)}</p>
            </div>
            <div className="transactions">
              <div className="transaction-icon" onClick={() => openModal('deposit')}>
                <div className="icon">💵</div>
                <h3>Deposit</h3>
              </div>
              <div className="transaction-icon" onClick={() => openModal('withdraw')}>
                <div className="icon">💸</div>
                <h3>Withdraw</h3>
              </div>
              <div className="transaction-icon" onClick={() => openModal('loan')}>
                <div className="icon">🏦</div>
                <h3>Apply Loan</h3>
              </div>
              <div className="transaction-icon" onClick={() => openModal('repay')}>
                <div className="icon">💳</div>
                <h3>Repay Loan</h3>
              </div>
            </div>
          </div>
        )}
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={closeModal}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>{getModalTitle()}</h2>
            <input 
              type="number" 
              placeholder="Enter amount in ₹" 
              value={modalAmount} 
              onChange={(e) => setModalAmount(e.target.value)}
              autoFocus
            />
            <div className="modal-buttons">
              <button onClick={handleTransaction} className="confirm-btn">Confirm</button>
              <button onClick={closeModal} className="cancel-btn">Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
