import {renderCustomers} from "./customer/view.js";

window.addEventListener('load', async event => {
    await renderCustomers()
})