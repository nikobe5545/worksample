/**
 * @param customerId {number}
 * @returns {Array}
 */
export const getAccountsForCustomer = async customerId => {
    try {
        const response = await fetch(`/api/customers/${customerId}/accounts`,
            {
                method: "GET",
                referrer: 'no-referrer',
                headers: {
                    "Content-Type": "application/json"
                }
            });
        return await response.json();
    } catch (exception) {
        console.error(`Failed to fetch accounts for customer with id ${customerId} with exception ${exception}`)
    }
}

/**
 *
 * @param customerId {Number}
 * @param initialCredit {Number}
 * @returns {Promise<void>}
 */
export const createNewAccount = async (customerId, initialCredit) => {
    try {
        const response = await fetch(`/api/customers/${customerId}/accounts`,
            {
                method: "POST",
                referrer: 'no-referrer',
                body: JSON.stringify({
                    customerId: customerId,
                    initialCredit: initialCredit
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            });
        return await response.json();
    } catch (exception) {
        console.error(`Failed to fetch accounts for customer with id ${customerId} with exception ${exception}`)
    }
}