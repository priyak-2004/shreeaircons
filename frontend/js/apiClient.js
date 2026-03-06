/**
 * API Client - FULL PUBLIC SYSTEM
 * Gallery, Reviews, Booking
 * ❌ No Login
 * ❌ No JWT
 * ✅ Customer Friendly
 */

const API_BASE_URL = 'http://localhost:5000/api';

class APIClient {

    // ---------------------------
    // BASIC FETCH
    // ---------------------------
    async fetch(endpoint, options = {}) {
        const url = `${API_BASE_URL}${endpoint}`;
        try {
            const response = await fetch(url, options);
            if (!response.ok) {
                throw new Error(`API Error: ${response.status} ${response.statusText}`);
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('API Error:', error);
            throw error;
        }
    }

    // ===========================
    // GALLERY (PUBLIC)
    // ===========================

    async getGallery(category = 'all') {
        const endpoint =
            category !== 'all'
                ? `/gallery?category=${category}`
                : '/gallery';

        const res = await this.fetch(endpoint, { method: 'GET' });
        return res.items || [];
    }

    async uploadGalleryItem(title, category, type, file) {
        const formData = new FormData();
        formData.append('title', title);
        formData.append('category', category);
        formData.append('type', type);
        formData.append('file', file);

        const response = await fetch(`${API_BASE_URL}/gallery/upload`, {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error(`Upload failed: ${response.status}`);
        }

        return response.json();
    }

    async deleteGalleryItem(id) {
        return this.fetch(`/gallery/delete/${id}`, {
            method: 'DELETE'
        });
    }

    // ===========================
    // BOOKINGS (PUBLIC)
    // ===========================

    async createBooking(bookingData) {
        return this.fetch('/bookings/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(bookingData)
        });
    }

    async getBookings() {
        const res = await this.fetch('/bookings', {
            method: 'GET'
        });
        return res.bookings || [];
    }

    async deleteBooking(id) {
        return this.fetch(`/bookings/delete/${id}`, {
            method: 'DELETE'
        });
    }

    // ===========================
    // REVIEWS (PUBLIC)
    // ===========================

    async createReview(reviewData) {
        return this.fetch('/reviews/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reviewData)
        });
    }

    async getAllReviews() {
        const res = await this.fetch('/reviews', {
            method: 'GET'
        });
        return res.reviews || [];
    }

    async deleteReview(id) {
        return this.fetch(`/reviews/delete/${id}`, {
            method: 'DELETE'
        });
    }
}

// GLOBAL INSTANCE
window.apiClient = new APIClient();
