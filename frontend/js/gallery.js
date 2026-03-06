const galleryGrid = document.getElementById("galleryGrid");
const emptyGallery = document.getElementById("emptyGallery");
const uploadBtn = document.getElementById("uploadBtn");
const fileInput = document.getElementById("galleryUpload");

let currentFilter = "all";

/* ===============================
   LOAD FROM LOCAL STORAGE
================================ */
function loadGallery() {
    const items = JSON.parse(localStorage.getItem("galleryItems")) || [];

    const filtered =
        currentFilter === "all"
            ? items
            : items.filter(i => i.category === currentFilter);

    if (filtered.length === 0) {
        galleryGrid.style.display = "none";
        emptyGallery.style.display = "block";
        return;
    }

    galleryGrid.style.display = "grid";
    emptyGallery.style.display = "none";
    displayGallery(filtered);
}

/* ===============================
   DISPLAY
================================ */
function displayGallery(items) {
    galleryGrid.innerHTML = "";

    items.forEach(item => {
        const div = document.createElement("div");
        div.className = "gallery-item";

        div.innerHTML =
            item.type === "image"
                ? `
                <img src="${item.src}">
                <div class="gallery-overlay">
                    <h4>${item.title}</h4>
                    <p>${item.category}</p>
                </div>`
                : `
                <video controls src="${item.src}"></video>
                <div class="gallery-overlay">
                    <h4>${item.title}</h4>
                    <p>${item.category}</p>
                </div>`;

        galleryGrid.appendChild(div);
    });
}

/* ===============================
   FILTER
================================ */
function filterGallery(category) {
    currentFilter = category;
    loadGallery();
}

/* ===============================
   UPLOAD CLICK
================================ */
uploadBtn.addEventListener("click", () => {
    fileInput.click(); // 👉 device gallery opens
});

/* ===============================
   FILE SELECT
================================ */
fileInput.addEventListener("change", () => {
    const file = fileInput.files[0];
    if (!file) return;

    const title = prompt("Enter title:");
    const category = prompt("AC / Washing / Refrigerator");

    if (!title || !category) {
        alert("Title & Category required");
        return;
    }

    const reader = new FileReader();
    reader.onload = () => {
        const data =
            JSON.parse(localStorage.getItem("galleryItems")) || [];

        data.push({
            title,
            category,
            type: file.type.startsWith("video") ? "video" : "image",
            src: reader.result
        });

        localStorage.setItem("galleryItems", JSON.stringify(data));
        loadGallery();
    };

    reader.readAsDataURL(file);
});

/* =============================== */
document.addEventListener("DOMContentLoaded", loadGallery);