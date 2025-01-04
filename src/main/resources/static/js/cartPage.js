document.addEventListener('DOMContentLoaded', async () => {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    let apiResponse = {};
    try {
        const response = await fetch(`/api/billing/calculate-cart-value`, {
            method: 'POST',
            body: JSON.stringify(cart),
            headers: {'Content-Type': 'application/json'}
        });
        apiResponse = await response.json();
    } catch (error) {
        console.error('Error:', error);
    }

    const priceFormatter = new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    });

    const totalCartPriceTag = document.getElementById('total-cart-price');
    totalCartPriceTag.textContent = `${priceFormatter.format(apiResponse.totalCartValue)}`

    const cartTables = document.getElementById('cart-tables');

    apiResponse.cartPackageValues.forEach(package => {
        const tableContainer = document.createElement('div');
        tableContainer.classList.add('card', 'border-0', 'shadow-sm', 'mb-4');
        tableContainer.style.backgroundColor = '#fff8f8';


        const formattedPrice = priceFormatter.format(package.pricePerPackage);

        const tableHeader = `
            <div class="card-body">
                <h4 class="card-title mb-4" style="color: #ff69b4;">${package.packageName} - ${package.quantity} pacotes. ${formattedPrice} por pacote.</h4>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr style="background-color: #ffecf2;">
                                <th scope="col">Donut</th>
                                <th scope="col">Quantidade</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tfoot>
                            <tr class="fw-bold" style="background-color: #ffecf2;">
                                <td colspan="1" class="text-end">Subtotal:</td>
                                <td id="total-${package.id}">R$ 0,00</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        `;

        tableContainer.innerHTML = tableHeader;
        cartTables.appendChild(tableContainer);
        document.getElementById(`total-${package.id}`).textContent = `R$ ${priceFormatter.format(package.totalPrice)}`;

        const tableBody = tableContainer.querySelector('tbody');

        package.donuts.forEach(donut => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>
                    <div class="d-flex align-items-center">
                        <img src="${donut.image}" class="rounded-circle me-2" style="width: 40px; height: 40px; object-fit: cover;">
                        <div>
                            <div class="fw-bold" style="color: #ff69b4;">${donut.title}</div>
                        </div>
                    </div>
                </td>
                <td>${donut.quantity}</td>
            `;
            tableBody.appendChild(row);

        });
    });
});
