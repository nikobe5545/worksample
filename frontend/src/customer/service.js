/**
 * @returns {Array}
 */
export const getAllCustomers = async () => {
    try {
        const response = await fetch('/api/customers',
            {
                method: "GET",
                referrer: 'no-referrer',
                headers: {
                    "Content-Type": "application/json"
                }
            });
        return await response.json();
    } catch (exception) {
        console.error(`Failed to fetch customers with exception ${exception}`)
    }
}

/**
 *
 * @param firstname {string}
 * @param lastname {string}
 * @returns {Promise<any>}
 */
export const createNewCustomer = async (firstname, lastname) => {
    try {
        const response = await fetch('/api/customers',
            {
                method: "POST",
                body: JSON.stringify({
                    "firstname": firstname,
                    "lastname": lastname
                }),
                referrer: 'no-referrer',
                headers: {
                    "Content-Type": "application/json"
                }
            });
        return await response.json();
    } catch (exception) {
        console.error(`Failed to fetch customers with exception ${exception}`)
    }
}