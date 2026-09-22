// ==========================================
// SCHOLARSHIP FINDER - FRONTEND JAVASCRIPT
// ==========================================

const API_URL = "/api/scholarships";


// ==========================================
// LOAD ALL SCHOLARSHIPS
// ==========================================

function loadAllScholarships() {

    const results =
        document.getElementById("scholarshipResults");

    results.innerHTML = "<p>Loading scholarships...</p>";

    fetch(API_URL)
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    `Server returned ${response.status}`
                );
            }

            return response.json();
        })
        .then(scholarships => {

            console.log("All scholarships:", scholarships);

            displayScholarships(scholarships);

        })
        .catch(error => {

            console.error("Load scholarships error:", error);

            results.innerHTML = `
                <div class="empty-message">

                    <div class="empty-icon">
                        ⚠️
                    </div>

                    <h3>Unable to load scholarships</h3>

                    <p>
                        ${error.message}
                    </p>

                </div>
            `;
        });
}


// ==========================================
// SEARCH BY SCHOLARSHIP NAME
// ==========================================

function searchScholarships() {

    const name =
        document.getElementById("searchInput").value.trim();

    if (name === "") {

        alert("Please enter a scholarship name.");

        return;
    }

    const results =
        document.getElementById("scholarshipResults");

    results.innerHTML = "<p>Searching...</p>";

    const url =
        `${API_URL}/name/${encodeURIComponent(name)}`;

    console.log("Search URL:", url);

    fetch(url)
        .then(response => {

            console.log(
                "Search response status:",
                response.status
            );

            if (!response.ok) {

                throw new Error(
                    `Server returned ${response.status}`
                );
            }

            return response.json();
        })
        .then(scholarships => {

            console.log(
                "Search results:",
                scholarships
            );

            displayScholarships(scholarships);

        })
        .catch(error => {

            console.error(
                "Search error:",
                error
            );

            results.innerHTML = `
                <div class="empty-message">

                    <div class="empty-icon">
                        ⚠️
                    </div>

                    <h3>
                        Something went wrong while searching.
                    </h3>

                    <p>
                        ${error.message}
                    </p>

                </div>
            `;
        });
}


// ==========================================
// SEARCH BY STATE
// ==========================================

function searchByState() {

    const state =
        prompt("Enter State:");

    if (!state || state.trim() === "") {
        return;
    }

    const results =
        document.getElementById("scholarshipResults");

    results.innerHTML = "<p>Searching...</p>";

    const url =
        `${API_URL}/state/${encodeURIComponent(state.trim())}`;

    console.log("State search URL:", url);

    fetch(url)
        .then(response => {

            console.log(
                "State response status:",
                response.status
            );

            if (!response.ok) {

                throw new Error(
                    `Server returned ${response.status}`
                );
            }

            return response.json();
        })
        .then(scholarships => {

            console.log(
                "State results:",
                scholarships
            );

            displayScholarships(scholarships);

        })
        .catch(error => {

            console.error(
                "State search error:",
                error
            );

            results.innerHTML = `
                <div class="empty-message">

                    <div class="empty-icon">
                        ⚠️
                    </div>

                    <h3>
                        Something went wrong while searching.
                    </h3>

                    <p>
                        ${error.message}
                    </p>

                </div>
            `;
        });
}


// ==========================================
// SEARCH BY CATEGORY
// ==========================================

function searchByCategory() {

    const category =
        prompt("Enter Category:");

    if (!category || category.trim() === "") {
        return;
    }

    const results =
        document.getElementById("scholarshipResults");

    results.innerHTML = "<p>Searching...</p>";

    const url =
        `${API_URL}/category/${encodeURIComponent(category.trim())}`;

    console.log("Category search URL:", url);

    fetch(url)
        .then(response => {

            console.log(
                "Category response status:",
                response.status
            );

            if (!response.ok) {

                throw new Error(
                    `Server returned ${response.status}`
                );
            }

            return response.json();
        })
        .then(scholarships => {

            console.log(
                "Category results:",
                scholarships
            );

            displayScholarships(scholarships);

        })
        .catch(error => {

            console.error(
                "Category search error:",
                error
            );

            results.innerHTML = `
                <div class="empty-message">

                    <div class="empty-icon">
                        ⚠️
                    </div>

                    <h3>
                        Something went wrong while searching.
                    </h3>

                    <p>
                        ${error.message}
                    </p>

                </div>
            `;
        });
}


// ==========================================
// SEARCH BY INCOME
// ==========================================

function searchByIncome() {

    const income =
        prompt("Enter your family income:");

    if (!income || income.trim() === "") {
        return;
    }

    if (isNaN(income)) {

        alert("Please enter a valid number.");

        return;
    }

    const results =
        document.getElementById("scholarshipResults");

    results.innerHTML = "<p>Searching...</p>";

    const url =
        `${API_URL}/income/${encodeURIComponent(income.trim())}`;

    console.log("Income search URL:", url);

    fetch(url)
        .then(response => {

            console.log(
                "Income response status:",
                response.status
            );

            if (!response.ok) {

                throw new Error(
                    `Server returned ${response.status}`
                );
            }

            return response.json();
        })
        .then(scholarships => {

            console.log(
                "Income results:",
                scholarships
            );

            displayScholarships(scholarships);

        })
        .catch(error => {

            console.error(
                "Income search error:",
                error
            );

            results.innerHTML = `
                <div class="empty-message">

                    <div class="empty-icon">
                        ⚠️
                    </div>

                    <h3>
                        Something went wrong while searching.
                    </h3>

                    <p>
                        ${error.message}
                    </p>

                </div>
            `;
        });
}


// ==========================================
// DISPLAY SCHOLARSHIPS
// ==========================================

function displayScholarships(scholarships) {

    const results =
        document.getElementById("scholarshipResults");

    if (!scholarships ||
        scholarships.length === 0) {

        results.innerHTML = `
            <div class="empty-message">

                <div class="empty-icon">
                    🎓
                </div>

                <h3>
                    No Scholarships Found
                </h3>

                <p>
                    Try another search or filter.
                </p>

            </div>
        `;

        return;
    }


    results.innerHTML = "";


    scholarships.forEach(scholarship => {

        const card =
            document.createElement("div");

        card.className =
            "scholarship-card";


        card.innerHTML = `

            <h3>
                ${scholarship.scholarshipName || "Scholarship"}
            </h3>

            <p>
                <strong>Category:</strong>
                ${scholarship.category || "Not specified"}
            </p>

            <p>
                <strong>State:</strong>
                ${scholarship.state || "India"}
            </p>

            <p>
                <strong>Income Limit:</strong>
                ₹${scholarship.incomeLimit || 0}
            </p>

            <p>
                <strong>Amount:</strong>
                ₹${scholarship.amount || 0}
            </p>

            <p>
                <strong>Eligibility:</strong>
                ${scholarship.eligibility || "Refer Official NSP"}
            </p>

            <p>
                <strong>Last Date:</strong>
                ${scholarship.lastDate || "Not specified"}
            </p>

            <a
                href="${scholarship.website || 'https://scholarships.gov.in'}"
                target="_blank"
                class="website-btn"
            >
                Visit Official Website
            </a>

        `;

        results.appendChild(card);

    });
}


// ==========================================
// ADMIN LOGIN
// ==========================================

function adminLogin() {

    alert(
        "Admin login will be connected next."
    );

}