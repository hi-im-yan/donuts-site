function updateQuantity(id, change) {
    const input = document.getElementById(`package_id-${id}`);
    const currentValue = parseInt(input.value);
    const newValue = currentValue + parseInt(change);

    input.value = newValue;

    const link = document.getElementById(`link-${id}`);
    link.setAttribute("href", `/packageId/${id}?packageAmount=${newValue}`);
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
}

function disableConfirmButton() {
    const confirmButton = document.getElementById('confirm-button');
    confirmButton.disabled = true;
    confirmButton.classList.remove('btn-pink-400');
    confirmButton.classList.add('btn-pink-100');
    confirmButton.style.pointerEvents = 'none';
}