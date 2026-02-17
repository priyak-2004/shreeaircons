/**
 * Shree Air Cons - Main JavaScript Utility File
 * Handles common functions and API calls
 */

class SACApi {
    constructor() {
        this.baseURL = '/api';
        this.adminURL = '/api/admin';
    }

    /**
     * Make API request
     */
    async request(endpoint, options = {}) {
        const url = `${this.baseURL}${endpoint}`;
        try {
            const response = await fetch(url, {
                headers: {
                    'Content-Type': 'application/json',
                    ...options.headers
                },
                ...options
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    /**
     * Booking APIs
     */
    createBooking(bookingData) {
        return this.request('/bookings', {
            method: 'POST',
            body: JSON.stringify(bookingData)
        });
    }

    getBooking(id) {
        return this.request(`/bookings/${id}`);
    }

    checkBookingStatus(phoneNumber) {
        return this.request(`/bookings/phone/${phoneNumber}`);
    }

    /**
     * Review APIs
     */
    submitReview(reviewData) {
        return this.request('/reviews', {
            method: 'POST',
            body: JSON.stringify(reviewData)
        });
    }

    getApprovedReviews() {
        return this.request('/reviews/approved');
    }

    getAverageRating() {
        return this.request('/reviews/rating');
    }

    /**
     * Gallery APIs
     */
    getGalleryImages() {
        return this.request('/gallery');
    }

    getGalleryImagesByService(serviceType) {
        return this.request(`/gallery/service/${serviceType}`);
    }

    /**
     * Admin APIs
     */
    adminRequest(endpoint, options = {}) {
        const url = `${this.adminURL}${endpoint}`;
        return fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        }).then(response => response.json());
    }

    getAdminStats() {
        return this.adminRequest('/dashboard/stats');
    }

    getAllBookings() {
        return this.adminRequest('/bookings');
    }

    updateBooking(id, bookingData) {
        return this.adminRequest(`/bookings/${id}`, {
            method: 'PUT',
            body: JSON.stringify(bookingData)
        });
    }

    getPendingReviews() {
        return this.adminRequest('/reviews/pending');
    }

    approveReview(id) {
        return this.adminRequest(`/reviews/${id}/approve`, {
            method: 'PUT'
        });
    }
}

// Initialize API client
const sacAPI = new SACApi();

/**
 * Form Validation Utilities
 */
const FormValidator = {
    validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    },

    validatePhone(phone) {
        const re = /^[0-9]{10}$/;
        return re.test(phone);
    },

    validatePhoneNumber(input) {
        const cleaned = input.replace(/\D/g, '');
        return cleaned.length === 10;
    },

    showError(message, elementId) {
        const errorDiv = document.getElementById(elementId);
        if (errorDiv) {
            errorDiv.innerHTML = `<div class="error-message"><i class="fas fa-exclamation-circle"></i> ${message}</div>`;
        }
    },

    showSuccess(message, elementId) {
        const successDiv = document.getElementById(elementId);
        if (successDiv) {
            successDiv.innerHTML = `<div class="success-message"><i class="fas fa-check-circle"></i> ${message}</div>`;
        }
    }
};

/**
 * Utility Functions
 */

/**
 * Format date to readable format
 */
function formatDate(date) {
    if (!date) return '';
    const d = new Date(date);
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    return `${d.getDate()} ${months[d.getMonth()]} ${d.getFullYear()}`;
}

/**
 * Format time
 */
function formatTime(date) {
    if (!date) return '';
    const d = new Date(date);
    return d.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
}

/**
 * Debounce function for search inputs
 */
function debounce(func, delay) {
    let timeoutId;
    return function (...args) {
        clearTimeout(timeoutId);
        timeoutId = setTimeout(() => func(...args), delay);
    };
}

/**
 * Show loading spinner
 */
function showLoader(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
        element.innerHTML = '<div class="loading"><div class="spinner"></div></div>';
    }
}

/**
 * Hide loading spinner
 */
function hideLoader(elementId) {
    const element = document.getElementById(elementId);
    if (element) {
        element.innerHTML = '';
    }
}

/**
 * Generate star rating HTML
 */
function generateStarRating(rating) {
    let html = '';
    for (let i = 0; i < 5; i++) {
        if (i < rating) {
            html += '<span style="color: #ffc107;">★</span>';
        } else {
            html += '<span style="color: #ddd;">★</span>';
        }
    }
    return html;
}

/**
 * Copy text to clipboard
 */
function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(() => {
        alert('Copied to clipboard!');
    });
}

/**
 * Generate WhatsApp message link
 */
function getWhatsAppLink(phoneNumber, message) {
    const encodedMessage = encodeURIComponent(message);
    return `https://wa.me/${phoneNumber}?text=${encodedMessage}`;
}

/**
 * Local Storage Utilities
 */
const StorageUtil = {
    set(key, value) {
        try {
            localStorage.setItem(key, JSON.stringify(value));
        } catch (e) {
            console.error('Storage error:', e);
        }
    },

    get(key) {
        try {
            const item = localStorage.getItem(key);
            return item ? JSON.parse(item) : null;
        } catch (e) {
            console.error('Storage error:', e);
            return null;
        }
    },

    remove(key) {
        try {
            localStorage.removeItem(key);
        } catch (e) {
            console.error('Storage error:', e);
        }
    },

    clear() {
        try {
            localStorage.clear();
        } catch (e) {
            console.error('Storage error:', e);
        }
    }
};

// Auto-format phone number input
document.addEventListener('DOMContentLoaded', function() {
    const phoneInputs = document.querySelectorAll('input[type="tel"]');
    phoneInputs.forEach(input => {
        input.addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length > 10) {
                value = value.substring(0, 10);
            }
            e.target.value = value;
        });
    });
});

// Console message
console.log('%cShree Air Cons', 'font-size: 24px; color: #1e5ba8; font-weight: bold;');
console.log('%cProfessional Appliance Repair Service', 'font-size: 12px; color: #666;');
