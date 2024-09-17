import React, { useState } from 'react';
import axios from 'axios';
import FetchComponent from './FetchComponent';  // Import the FetchComponent

const FormComponent = () => {
    const [formData, setFormData] = useState({
        action: '',
        fromAccount: '',
        sweepDay: '',
        toAccount: '',
        resultantBalance: '',
        status: ''
    });

    const [formErrors, setFormErrors] = useState({
        fromAccount: '',
        toAccount: '',
        resultantBalance: '',
        status: ''
    });

    const validateResultantBalance = (balance) => {
        const balanceNumber = Number(balance);

        // Check if balance is a valid integer
        if (isNaN(balanceNumber) || !Number.isInteger(balanceNumber)) {
            return 'Resultant balance must be an integer';
        }

        // Convert to string and check length
        const balanceStr = balanceNumber.toString();
        if (balanceStr.length > 16) {
            return 'Resultant balance length must be less than 17 digits';
        }

        return '';
    };

    const validateForm = () => {
        let isValid = true;
        let errors = {
            fromAccount: '',
            toAccount: '',
            resultantBalance: '',
            status: ''
        };

        // Check for empty fields
        for (const key in formData) {
            if (formData[key] === '') {
                alert('All fields are mandatory');
                isValid = false;
                return isValid;
            }
        }

        // Validate account numbers
        if (formData.fromAccount.length !== 10 || isNaN(Number(formData.fromAccount))) {
            errors.fromAccount = 'Invalid account number';
            isValid = false;
        }
        if (formData.toAccount.length !== 10 || isNaN(Number(formData.toAccount))) {
            errors.toAccount = 'Invalid account number';
            isValid = false;
        }

        // Validate resultant balance
        const balanceError = validateResultantBalance(formData.resultantBalance);
        if (balanceError) {
            errors.resultantBalance = balanceError;
            isValid = false;
        }

        // Validate status based on action
        if (formData.action === 'Create' && formData.status !== 'Inactive') {
            errors.status = 'Status should be inactive during create';
            isValid = false;
        }

        setFormErrors(errors);
        return isValid;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validateForm()) {
            try {
                await axios.post('http://localhost:8080/api/save', formData);
                alert('Data transmitted successfully');
            } catch (error) {
                console.error('Error transmitting data:', error);
                alert('Failed to transmit data');
            }
        }
    };

    return (
        <div>
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
                        <input
                            type="text"
                            name="fromAccount"
                            value={formData.fromAccount}
                            onChange={handleChange}
                            required
                        />
                        {formErrors.fromAccount && <span>{formErrors.fromAccount}</span>}
                    </label>
                </div>
                <div>
                    <label>
                        Sweep Day:
                        <input
                            type="text"
                            name="sweepDay"
                            value={formData.sweepDay}
                            onChange={handleChange}
                            required
                        />
                    </label>
                </div>
                <div>
                    <label>
                        To Account:
                        <input
                            type="text"
                            name="toAccount"
                            value={formData.toAccount}
                            onChange={handleChange}
                            required
                        />
                        {formErrors.toAccount && <span>{formErrors.toAccount}</span>}
                    </label>
                </div>
                <div>
                    <label>
                        Resultant Balance:
                        <input
                            type="text"
                            name="resultantBalance"
                            value={formData.resultantBalance}
                            onChange={handleChange}
                            required
                        />
                        {formErrors.resultantBalance && <span>{formErrors.resultantBalance}</span>}
                    </label>
                </div>
                <div>
                    <label>
                        Status:
                        <select
                            name="status"
                            value={formData.status}
                            onChange={handleChange}
                            disabled={formData.action === 'Create'}
                            required
                        >
                            <option value="">Select Status</option>
                            <option value="Active">Active</option>
                            <option value="Inactive">Inactive</option>
                        </select>
                        {formErrors.status && <span>{formErrors.status}</span>}
                    </label>
                </div>
                <button type="submit">Transmit</button>
            </form>

            {/* FetchComponent included here */}
            <FetchComponent setFormData={setFormData} setFormErrors={setFormErrors} />
        </div>
    );
};

export default FormComponent;
