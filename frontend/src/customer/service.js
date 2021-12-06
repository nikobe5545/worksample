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