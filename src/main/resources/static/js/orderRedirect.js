document.addEventListener('DOMContentLoaded', async () => {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    let apiResponse = {};
    try {
        const response = await fetch(`/api/whatsapp`, {
            method: 'POST',
            body: JSON.stringify(cart),
            headers: {'Content-Type': 'application/json'}
        });

        apiResponse = await response.json();

        document.getElementById('redirect-url').href = apiResponse.redirectUrl;

        window.open(apiResponse.redirectUrl, '_blank');

    setTimeout(function() {
         window.location.href = '/';
    }, 15000);
    } catch (error) {
        console.error('Error:', error);
    }
})
