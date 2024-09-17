import React, { useState } from 'react';
import axios from 'axios';

const FetchComponent = ({ setFormData, setFormErrors }) => {
    const [fromAccount, setFromAccount] = useState('');

    const handleFetch = async () => {
        if (!fromAccount) {
            alert('Please fill from_account_no');
            return;
        }

        try {
            const response = await axios.get(`http://localhost:8080/api/fetch/${fromAccount}`);
            if (response.data) {
                setFormData(response.data);
                setFormErrors({
                    fromAccount: '',
                    toAccount: '',
                    resultantBalance: '',
                    status: ''
                });
            } else {
                alert('Record not found');
            }
        } catch (error) {
            console.error('Error fetching data:', error);
            alert('Error fetching data');
        }
    };

    return (
        <div>
            <input
                type="text"
                placeholder="Enter From Account No"
                value={fromAccount}
                onChange={(e) => setFromAccount(e.target.value)}
            />
            <button onClick={handleFetch}>Fetch</button>
        </div>
    );
};

export default FetchComponent;
