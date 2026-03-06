// ============================================
// CONTACT/BOOKING FUNCTIONALITY
// ============================================

const bookingForm = document.getElementById('bookingForm');

// Handle booking form submission
function submitBooking(event) {
    event.preventDefault();

    const name = document.getElementById('custName').value;
    const phone = document.getElementById('custPhone').value;
    const address = document.getElementById('custAddress').value;
    const service = document.getElementById('serviceType').value;
    const description = document.getElementById('description').value;

    // Validate
    if (!name || !phone || !address || !service) {
        alert("Please fill all required fields");
        return;
    }

    if (!/^\d{10}$/.test(phone)) {
        alert("Enter valid 10 digit phone number");
        return;
    }

    // WhatsApp message
    const message =
`New Service Booking

Name: ${name}
Phone: ${phone}
Address: ${address}
Service: ${service}
Problem: ${description}`;

    const whatsappNumber = "916369529001";

    const url = `https://wa.me/${whatsappNumber}?text=${encodeURIComponent(message)}`;

    window.open(url, "_blank");

    document.getElementById("bookingForm").reset();
}
// Show confirmation modal
function showConfirmationModal(booking) {
    const modal = document.createElement('div');
    modal.className = 'modal show';
    modal.innerHTML = `
        <div class="modal-content">
            <button class="modal-close" onclick="this.parentElement.parentElement.remove()">✕</button>
            <div class="success-message" style="margin-bottom: 1.5rem; font-size: 1.2rem;">
                ✅ Booking Confirmed!
            </div>
            <h3 style="color: var(--primary-blue); margin-bottom: 1.5rem;">Booking Details</h3>
            <div style="background: var(--light-gray); padding: 1.5rem; border-radius: 8px; margin-bottom: 1.5rem;">
                <p><strong>Name:</strong> ${booking.name}</p>
                <p><strong>Phone:</strong> ${booking.phone}</p>
                <p><strong>Address:</strong> ${booking.address}</p>
                <p><strong>Service Type:</strong> ${booking.service}</p>
                <p><strong>Booking Date:</strong> ${formatDate(booking.createdAt)}</p>
                <p><strong>Booking Time:</strong> ${formatTime(booking.createdAt)}</p>
            </div>
            <div style="background: #e8f5e9; padding: 1rem; border-radius: 8px; margin-bottom: 1.5rem; border-left: 4px solid #4CAF50;">
                <p style="margin: 0; color: #2e7d32;">✓ We have received your booking request and will contact you shortly.</p>
                <p style="margin: 0.5rem 0 0 0; color: #2e7d32;">✓ Confirmation email has been sent to your email.</p>
                <p style="margin: 0.5rem 0 0 0; color: #2e7d32;">✓ WhatsApp notification sent to our team.</p>
            </div>
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                <button class="btn btn-primary" onclick="window.location.href='../index.html'">Back to Home</button>
                <button class="btn btn-secondary" onclick="this.parentElement.parentElement.parentElement.remove()">Close</button>
            </div>
        </div>
    `;
    document.body.appendChild(modal);

    // Auto remove modal after 12 seconds
    setTimeout(() => {
        if (modal.parentElement) {
            modal.remove();
        }
    }, 12000);
}
