// ============================================
// SHREE AIR CONS - MAIN JAVASCRIPT
// ============================================

// DOM Elements (with null checks for elements that don't exist on all pages)
const hamburger = document.querySelector('.hamburger');
const nav = document.querySelector('nav');
const navLinks = document.querySelectorAll('nav a');
const scrollTopBtn = document.querySelector('.scroll-top-btn');
const callBtn = document.querySelector('.call-btn');
const whatsappBtn = document.querySelector('.whatsapp-btn');

// Configuration
const WHATSAPP_NUMBER = '6395290014';
const CALL_NUMBERS = ['6369529001', '9342017154'];

// ============================================
// HAMBURGER MENU
// ============================================

if (hamburger) {
    hamburger.addEventListener('click', () => {
        nav.classList.toggle('active');
        hamburger.classList.toggle('active');

        // Animate hamburger
        const spans = hamburger.querySelectorAll('span');
        if (hamburger.classList.contains('active')) {
            spans[0].style.transform = 'rotate(45deg) translate(10px, 10px)';
            spans[1].style.opacity = '0';
            spans[2].style.transform = 'rotate(-45deg) translate(8px, -8px)';
        } else {
            spans[0].style.transform = '';
            spans[1].style.opacity = '1';
            spans[2].style.transform = '';
        }
    });

    // Close menu on link click
    navLinks.forEach(link => {
        link.addEventListener('click', () => {
            nav.classList.remove('active');
            hamburger.classList.remove('active');
            const spans = hamburger.querySelectorAll('span');
            spans[0].style.transform = '';
            spans[1].style.opacity = '1';
            spans[2].style.transform = '';
        });
    });
}

// ============================================
// SCROLL TO TOP BUTTON
// ============================================

if (scrollTopBtn) {
    window.addEventListener('scroll', () => {
        if (window.pageYOffset > 300) {
            scrollTopBtn.classList.remove('hidden');
        } else {
            scrollTopBtn.classList.add('hidden');
        }
    });

    scrollTopBtn.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

// ============================================
// STICKY CALL BUTTON
// ============================================

if (callBtn) {
    callBtn.addEventListener('click', () => {
        const number = CALL_NUMBERS[0];
        window.location.href = `tel:+91${number}`;
    });
}

// ============================================
// STICKY WHATSAPP BUTTON
// ============================================

if (whatsappBtn) {
    whatsappBtn.addEventListener('click', () => {
        const message = encodeURIComponent(
            'Hello! I would like to book a service for my air conditioner. Could you please help?'
        );
        window.location.href = `https://wa.me/91${WHATSAPP_NUMBER}?text=${message}`;
    });
}

// ============================================
// SET ACTIVE NAV LINK
// ============================================

function setActiveNavLink() {
    const currentPage = window.location.pathname.split('/').pop() || 'index.html';
    navLinks.forEach(link => {
        const href = link.getAttribute('href');
        if (href === currentPage || (currentPage === '' && href === 'index.html')) {
            link.classList.add('active');
        } else {
            link.classList.remove('active');
        }
    });
}

// Call on page load
window.addEventListener('load', setActiveNavLink);

// ============================================
// SCROLL ANIMATIONS
// ============================================

const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -100px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
            observer.unobserve(entry.target);
        }
    });
}, observerOptions);

document.addEventListener('DOMContentLoaded', () => {
    const animatedElements = document.querySelectorAll('section, .service-card, .review-item, .gallery-item');
    animatedElements.forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(el);
    });
});

// ============================================
// TOAST NOTIFICATION
// ============================================

function showNotification(message, type = 'success-message', duration = 3000) {
    const notification = document.createElement('div');
    notification.className = type;
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 2rem;
        right: 2rem;
        padding: 1rem 1.5rem;
        border-radius: 5px;
        z-index: 10000;
        animation: slideInRight 0.3s ease;
        max-width: 300px;
        background: ${type === 'success-message' ? '#4CAF50' : '#f44336'};
        color: white;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    `;

    document.body.appendChild(notification);

    setTimeout(() => {
        notification.style.animation = 'slideInRight 0.3s ease reverse';
        setTimeout(() => notification.remove(), 300);
    }, duration);
}

// ============================================
// UTILITY FUNCTIONS
// ============================================

function formatDate(date) {
    return new Date(date).toLocaleDateString('en-IN', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    });
}

function formatTime(date) {
    return new Date(date).toLocaleTimeString('en-IN', {
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Export for use in other files
window.showNotification = showNotification;
window.formatDate = formatDate;
window.formatTime = formatTime;
window.WHATSAPP_NUMBER = WHATSAPP_NUMBER;
window.CALL_NUMBERS = CALL_NUMBERS;
