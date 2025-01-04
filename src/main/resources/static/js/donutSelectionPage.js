// variables mapped from the backend
const pageVariables = {
        "packageId": document.getElementById("packageId").value,
        "packageAmount": document.getElementById("packageAmount").value,
        "packageName": document.getElementById("packageName").value,
        "amountOfDonutsToChoose": document.getElementById("amountOfDonutsToChoose").value,
        "donutMenu": document.getElementById("donutMenu").value,
    }

function updateQuantity(id, change) {
    const input = document.getElementById(`donut_id-${id}`);
    const currentValue = parseInt(input.value);
    const newValue = currentValue + parseInt(change);

    const amountOfDonutsToChoose = parseInt(pageVariables.amountOfDonutsToChoose);

    if (newValue >= 0 && newValue <= amountOfDonutsToChoose) {
        const total = calculateTotal() + change;

        if (total > amountOfDonutsToChoose) {
            // This prevents the <total> variable to +1 when reaching the max amount of donuts
            return;
        }

        if (total <= amountOfDonutsToChoose) {
            input.value = newValue;
            updateTotal();
        }

        total === amountOfDonutsToChoose ? enableConfirmButton() : disableConfirmButton();
    }
}

function updateTotal() {
    const total = calculateTotal();
    document.getElementById('total-count').textContent = total;
}

function calculateTotal() {
    let total = 0;
    const inputs = document.querySelectorAll('.quantity-input');
    inputs.forEach(input => {
        total += parseInt(input.value);
    });
    return total;
}

function enableConfirmButton() {
    const confirmButton = document.getElementById('confirm-button');
    confirmButton.disabled = false;
    confirmButton.classList.remove('btn-pink-100');
    confirmButton.classList.add('btn-pink-400');
    confirmButton.style.pointerEvents = 'auto';
    confirmButton.onclick = () => {
        addToCart();
    };
}

function disableConfirmButton() {
    const confirmButton = document.getElementById('confirm-button');
    confirmButton.disabled = true;
    confirmButton.classList.remove('btn-pink-400');
    confirmButton.classList.add('btn-pink-100');
    confirmButton.style.pointerEvents = 'none';
    confirmButton.onclick = null;
}

function addToCart() {
    const donuts = [];
    const inputs = document.querySelectorAll('.quantity-input');
    inputs.forEach(input => {
        const id = input.id.split('-')[1];
        const amount = parseInt(input.value);
        if (amount > 0) {
            donuts.push({"donutId": id, "quantity": amount});
        }
    });

    const newPackage = {
        "packageId": pageVariables.packageId,
        "packageAmount": pageVariables.packageAmount,
        "packageName": pageVariables.packageName,
        "donuts": donuts
    };

    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    const packageIndex = cart.findIndex(pkg => pkg.packageId === newPackage.packageId);

    // updating the package if it already exists in the cart
    if (packageIndex > -1) {
        cart[packageIndex] = newPackage;
    } else {
        cart.push(newPackage);
    }

    localStorage.setItem("cart", JSON.stringify(cart));
    showCartToast();

    setTimeout(() => {
        window.location.href = "/";
    }, 4000);
}

function showCartToast() {
    const toastElement = document.getElementById('cartToast');
    const toast = new bootstrap.Toast(toastElement);
    toast.show();
}