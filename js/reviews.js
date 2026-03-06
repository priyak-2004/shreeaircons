// ============================================
// REVIEWS – NO BACKEND | EDIT + DELETE | HTML MATCH
// ============================================

let editIndex = null;

document.addEventListener("DOMContentLoaded", () => {
    setupStars();
    loadReviews();
});

/* ===============================
   STAR RATING
=============================== */
function setupStars() {
    document.querySelectorAll(".star").forEach(star => {
        star.addEventListener("click", () => {
            const value = star.dataset.value;
            document.getElementById("ratingValue").value = value;

            document.querySelectorAll(".star").forEach(s => {
                s.style.color = s.dataset.value <= value ? "#FFD700" : "#ccc";
            });
        });
    });
}

/* ===============================
   SUBMIT REVIEW (HTML calls this)
=============================== */
function submitReview(event) {
    event.preventDefault();

    const name = document.getElementById("reviewName").value.trim();
    const message = document.getElementById("reviewMessage").value.trim();
    const rating = parseInt(document.getElementById("ratingValue").value);

    if (!name || !message || rating === 0) {
        alert("All fields & rating required");
        return;
    }

    const reviews = JSON.parse(localStorage.getItem("reviews")) || [];

    if (editIndex !== null) {
        // ✏️ UPDATE
        reviews[editIndex].name = name;
        reviews[editIndex].message = message;
        reviews[editIndex].rating = rating;
        editIndex = null;
    } else {
        // ➕ NEW
        reviews.unshift({
            name,
            message,
            rating,
            date: new Date().toISOString()
        });
    }

    localStorage.setItem("reviews", JSON.stringify(reviews));
    resetForm();
    loadReviews();
}

/* ===============================
   LOAD REVIEWS
=============================== */
function loadReviews() {
    const reviews = JSON.parse(localStorage.getItem("reviews")) || [];
    const list = document.getElementById("reviewsList");
    const noReviews = document.getElementById("noReviews");
    const avgRating = document.getElementById("avgRating");
    const totalReviews = document.getElementById("totalReviews");

    list.innerHTML = "";

    if (reviews.length === 0) {
        noReviews.style.display = "block";
        avgRating.textContent = "0";
        totalReviews.textContent = "0";
        return;
    }

    noReviews.style.display = "none";

    let total = 0;

    reviews.forEach((r, i) => {
        total += r.rating;

        const div = document.createElement("div");
        div.className = "review-item";

        div.innerHTML = `
            <strong>${r.name}</strong>
            <div style="color:#FFD700;">${"★".repeat(r.rating)}${"☆".repeat(5 - r.rating)}</div>
            <p>${r.message}</p>
            <button onclick="editReview(${i})">Edit</button>
            <button onclick="deleteReview(${i})">Delete</button>
        `;

        list.appendChild(div);
    });

    avgRating.textContent = (total / reviews.length).toFixed(1);
    totalReviews.textContent = reviews.length;
}

/* ===============================
   EDIT
=============================== */
function editReview(index) {
    const reviews = JSON.parse(localStorage.getItem("reviews")) || [];
    const r = reviews[index];

    document.getElementById("reviewName").value = r.name;
    document.getElementById("reviewMessage").value = r.message;
    document.getElementById("ratingValue").value = r.rating;

    document.querySelectorAll(".star").forEach(s => {
        s.style.color = s.dataset.value <= r.rating ? "#FFD700" : "#ccc";
    });

    editIndex = index;
    window.scrollTo({ top: 300, behavior: "smooth" });
}

/* ===============================
   DELETE
=============================== */
function deleteReview(index) {
    if (!confirm("Delete this review?")) return;

    const reviews = JSON.parse(localStorage.getItem("reviews")) || [];
    reviews.splice(index, 1);
    localStorage.setItem("reviews", JSON.stringify(reviews));
    loadReviews();
}

/* ===============================
   RESET FORM
=============================== */
function resetForm() {
    document.getElementById("reviewForm").reset();
    document.getElementById("ratingValue").value = 0;
    document.querySelectorAll(".star").forEach(s => s.style.color = "#ccc");
}