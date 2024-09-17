import React, { useState } from 'react';
import axios from 'axios';

const FormComponent = () => {
    const [formData, setFormData] = useState({
        action: '',
        fromAccount: '',
        sweepDay: '',
        toAccount: '',
        resultantBalance: '',
        status: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8080/api/save', formData);
            alert('Data transmitted successfully');
        } catch (error) {
            console.error('Error transmitting data:', error);
            alert('Failed to transmit data');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>
                    Action:
                    <select name="action" value={formData.action} onChange={handleChange} required>
                        <option value="">Select Action</option>
                        <option value="Create">Create</option>
                        <option value="Amend">Amend</option>
                    </select>
                </label>
            </div>
            <div>
                <label>
                    From Account:
                    <input type="text" name="fromAccount" value={formData.fromAccount} onChange={handleChange} required />
                </label>
            </div>
            <div>
                <label>
                    Sweep Day:
                    <input type="text" name="sweepDay" value={formData.sweepDay} onChange={handleChange} required />
                </label>
            </div>
            <div>
                <label>
                    To Account:
                    <input type="text" name="toAccount" value={formData.toAccount} onChange={handleChange} required />
                </label>
            </div>
            <div>
                <label>
                    Resultant Balance:
                    <input type="text" name="resultantBalance" value={formData.resultantBalance} onChange={handleChange} required />
                </label>
            </div>
            <div>
                <label>
                    Status:
                    <select name="status" value={formData.status} onChange={handleChange} required>
                        <option value="">Select Status</option>
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </label>
            </div>
            <button type="submit">Transmit</button>
        </form>
    );
};

export default FormComponent;
