import React, { useState, useEffect } from 'react';
import axios from 'axios';

// CSS styling for the form
import './FormComponent.css';

const FormComponent = () => {
    const [action, setAction] = useState('');
    const [fromAccount, setFromAccount] = useState('');
    const [sweepDay, setSweepDay] = useState('');
    const [toAccount, setToAccount] = useState('');
    const [resultantBalance, setResultantBalance] = useState('');
    const [status, setStatus] = useState('Inactive');
    const [isFetchMode, setIsFetchMode] = useState(false);

    // Function to fetch data based on `fromAccount`
    const fetchRecord = () => {
        if (!fromAccount) {
            alert('Please fill From Account No.');
            return;
        }

        axios.get(`/api/fetch/${fromAccount}`)
            .then(response => {
                if (response.data) {
                    const data = response.data;
                    setAction('Amend');
                    setSweepDay(data.sweepDay);
                    setToAccount(data.toAccount);
                    setResultantBalance(data.resultantBalance);
                    setStatus(data.status);
                    setIsFetchMode(true);
                } else {
                    alert('Record not found');
                }
            })
            .catch(error => {
                console.error('There was an error fetching the record!', error);
            });
    };

    // Function to handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        if (!action || !fromAccount || !sweepDay || !toAccount || !resultantBalance) {
            alert('All fields are mandatory');
            return;
        }

        if (fromAccount.length !== 10 || toAccount.length !== 10) {
            alert('Invalid account number');
            return;
        }

        if (!/^\d{1,16}$/.test(resultantBalance)) {
            alert('Resultant balance should be an integer and less than 17 digits');
            return;
        }

        if (action === 'Create') {
            axios.post('/api/save', {
                action,
                fromAccount,
                sweepDay,
                toAccount,
                resultantBalance,
                status
            })
                .then(response => {
                    alert('Data saved successfully');
                })
                .catch(error => {
                    alert('Failed to save data');
                });
        } else if (action === 'Amend') {
            axios.put(`/api/update/${fromAccount}`, {
                action,
                sweepDay,
                toAccount,
                resultantBalance,
                status
            })
                .then(response => {
                    alert('Data updated successfully');
                })
                .catch(error => {
                    alert('Failed to update data');
                });
        }
    };

    useEffect(() => {
        if (action === 'Create') {
            setStatus('Inactive'); // Lock status field to Inactive
        }
    }, [action]);

    return (
        <div className="form-container">
            <h1>Sweep Data Form</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Action</label>
                    <select
                        value={action}
                        onChange={(e) => {
                            setAction(e.target.value);
                            if (e.target.value === 'Amend') {
                                setIsFetchMode(true);
                            } else {
                                setIsFetchMode(false);
                                setStatus('Inactive');
                            }
                        }}
                    >
                        <option value="">Select Action</option>
                        <option value="Create">Create</option>
                        <option value="Amend">Amend</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>From Account No</label>
                    <input
                        type="text"
                        value={fromAccount}
                        onChange={(e) => setFromAccount(e.target.value)}
                    />
                    {action === 'Amend' && (
                        <button type="button" onClick={fetchRecord}>
                            Fetch
                        </button>
                    )}
                </div>
                <div className="form-group">
                    <label>Sweep Day</label>
                    <input
                        type="text"
                        value={sweepDay}
                        onChange={(e) => setSweepDay(e.target.value)}
                        disabled={!isFetchMode}
                    />
                </div>
                <div className="form-group">
                    <label>To Account No</label>
                    <input
                        type="text"
                        value={toAccount}
                        onChange={(e) => setToAccount(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>Resultant Balance</label>
                    <input
                        type="text"
                        value={resultantBalance}
                        onChange={(e) => setResultantBalance(e.target.value)}
                    />
                </div>
                <div className="form-group">
                    <label>Status</label>
                    <select
                        value={status}
                        onChange={(e) => setStatus(e.target.value)}
                        disabled={action === 'Create'}
                    >
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </div>
                <button type="submit">Transmit</button>
            </form>
        </div>
    );
};

export default FormComponent;
