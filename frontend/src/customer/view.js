import {createNewCustomer, getAllCustomers} from "./service.js";
import {createNewAccount, getAccountsForCustomer} from "../account/service.js";

const renderAccountsTable = (html, accounts) => {
    html =
        "<table class='table table-striped'>" +
        "<thead>" +
        "   <tr>" +
        "       <th>#</th>" +
        "       <th>Id</th>" +
        "       <th>Created</th>" +
        "       <th>Balance</th>" +
        "   </tr>" +
        "</thead>" +
        "<tbody>"
    accounts.forEach(account => {
        html += `<tr data-bs-toggle="collapse" data-bs-target="#account${account.id}" class="accordion-toggle collapsed">
                            <td class="expand-button"></td>
                            <td>${account.id}</td>
                            <td>${new Date(account.created).toLocaleString()}</td>
                            <td>${account.balance}</td>
                         </tr>`
        if (account.transactions.length > 0) {
            account.transactions.forEach(transaction => {
                html += `<tr>
                                <td colspan="12" class="hiddenRow">
                                    <div class="accordian-body collapse" id="account${account.id}">
                                        <table class="table table-dark table-striped">
                                            <thead>
                                                <tr class="info">
                                                    <th>Transaction id</th>
                                                    <th>Created</th>
                                                    <th>Amount</th>
                                                    <th>Type</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>${transaction.id}</td>
                                                    <td>${new Date(transaction.created).toLocaleString()}</td>
                                                    <td>${transaction.amount}</td>
                                                    <td>${transaction.type}</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>`
            })
        }
    })
    html += "</table>"
    return html;
}

const renderAccounts = async event => {
    const button = event.target
    const customerId = parseInt(button.dataset.customerId)
    const accounts = await getAccountsForCustomer(customerId)
    let html = "<p>Customer has no accounts</p>"
    if (accounts.length > 0) {
        html = renderAccountsTable(html, accounts);
    }
    document.getElementById("accounts-modal-body").innerHTML = html
}

const generateAccountFormModalBody = event => {
    const customerId = event.target.dataset.customerId
    const customerFirstname = event.target.dataset.customerFirstname
    const customerLastname = event.target.dataset.customerLastname
    let modalBody = document.getElementById("add-account-modal-body")
    modalBody.innerHTML =
        `<form>
                <div class="modal-body mb-3">
                    <legend>Add account for ${customerFirstname} ${customerLastname}</legend>
                </div>
                <div class="modal-body mb-3">
                    <label for="initial-credit" class="form-label">Initial credit</label>
                    <input type="number" class="form-control" id="initial-credit" aria-describedby="initial-credit-help">
                    <div id="initial-credit-help" class="form-text">Input a positive number for adding an initial credit. Leave empty or set to zero if no initial credit should be set.</div>
                </div>
                <input type="hidden" name="customerId" value="${customerId}" id="customer-id-input"/>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <span class="btn btn-primary" id="create-account-button">Create account</span>
                </div>
            </form>`
}

const submitAccountForm = async () => {
    let modalBody = document.getElementById("add-account-modal-body")
    const customerId = parseInt(document.getElementById("customer-id-input").value)
    const initialCredit = parseInt(document.getElementById("initial-credit").value)
    const newAccount = await createNewAccount(customerId, initialCredit)
    modalBody.innerHTML = `
                <div class="modal-body">
                    <h5>Account with id ${newAccount.id} was added</h5>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>`
}

const submitNewCustomerForm = async () => {
    const firstnameInput = document.getElementById("firstname")
    const lastnameInput = document.getElementById("lastname")
    const customer = await createNewCustomer(firstnameInput.value, lastnameInput.value)
    firstnameInput.value = ""
    lastnameInput.value = ""
    await renderCustomers()
}

const addButtonListeners = () => {
    document.getElementById("create-customer-submit-button").onclick = async event => {
        await submitNewCustomerForm()
    }
    Array.from(document.querySelectorAll("button.view-account")).forEach(button => {
        button.onclick = async event => {
            await renderAccounts(event);
        }
    })
    Array.from(document.querySelectorAll("button.create-new-account")).forEach(button => {
        button.onclick = event => {
            // Render form in modal
            generateAccountFormModalBody(event);
            document.getElementById("create-account-button").onclick = async () => {
                await submitAccountForm();
            }
        }
    })
}

export const renderCustomers = async () => {
    const customers = await getAllCustomers()
    let html = ""
    customers.forEach(customer => {
        html += `<tr>
                    <td>${customer.id}</td>
                    <td>${new Date(customer.created).toLocaleString()}</td>
                    <td>${customer.firstname}</td>
                    <td>${customer.lastname}</td>
                    <td><button type="button" class="view-account btn btn-primary" data-customer-id="${customer.id}" data-bs-toggle="modal" data-bs-target="#accounts-modal">View accounts</button></td>
                    <td><button type="button" class="create-new-account btn btn-primary" data-customer-id="${customer.id}" 
                    data-customer-firstname="${customer.firstname}" data-customer-lastname="${customer.lastname}"
                    data-bs-toggle="modal" data-bs-target="#add-account-modal">Create new account</button></td>
                 </tr>`
    })
    document.querySelector("table#customer-table tbody").innerHTML = html
    addButtonListeners();
}