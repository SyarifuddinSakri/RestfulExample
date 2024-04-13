const hostname = window.location.hostname;
const url = `http://${hostname}:8080`

function getUsers(){
    fetch(`${url}/users`,
    {
        method:'GET',
        mode:'cors',
        headers:{'Content-Type':'application/json'}
    }
    ).then(response => response.json()).then(data => console.log(data))
}

function createUser(){
    let firstName = document.getElementById('inputUserFirstName').value;
    let lastName = document.getElementById('inputUserLastName').value;
    let age = parseInt(document.getElementById('inputUserAge').value); 
    let occupation = document.getElementById('inputUserOccupation').value;
    let email = document.getElementById('inputUserMail').value;
    let joinDate = document.getElementById('inputUserJoinDate').value;

    userData = {
        "firstName":firstName,
        "lastName":lastName,
        "age":age,
        "occupation":occupation,
        "email":email,
        "joinDate":joinDate
    }
    console.log(userData)

    fetch(`${url}/create`,{
        method:'POST',
        mode:'cors',
        body: JSON.stringify(userData),
        headers:{'Content-Type':'application/json'}
    })
}

function updateUser(){
    let id = parseInt(document.getElementById('updateUserID').value);
    let firstName = document.getElementById('updateUserFirstName').value;
    let lastName = document.getElementById('updateUserLastName').value;
    let age = parseInt(document.getElementById('updateUserAge').value);
    let occupation = document.getElementById('updateUserOccupation').value;
    let email = document.getElementById('updateUserMail').value;
    let joinDate = document.getElementById('updateUserJoinDate').value;

    newUserData = {
        "firstName" : firstName,
        "lastName" : lastName,
        "age" : age,
        "occupation" : occupation,
        "email" : email,
        "joinDate": joinDate
    }

    fetch(`${url}/update/${id}`,{
        method : 'PUT',
        mode : 'cors',
        body : JSON.stringify(newUserData),
        headers : {'Content-Type':'application/json'}
    })
}

function deleteUser(){
    let id = document.getElementById('deleteUserID').value;

    fetch(`${url}/delete/${id}`,{
        mode : 'cors',
        method : 'DELETE'
    }).then(response => {
        if(response.ok){
            console.log(`The data of ID ${id} has been removed from dataBase`)
        }else{
            throw new Error('Failed to delete the data');
        }
    }).catch(error => {
        console.error(error);
    })
}

function filterByAgeBetween(){
    let min = document.getElementById('minAgeInput').value;
    let max = document.getElementById('maxAgeInput').value;

    fetch(`${url}/users/ageRange?minAge=${min}&maxAge=${max}`,
    {
        method : 'GET',
        mode : 'cors',
        headers:{'Content-Type':'application/json'}
    }
    ).then(response => response.json()).then(data => console.log(data));
}

function filterByMinAge(){
    let min = document.getElementById('minAge').value;

    fetch(`${url}/users/ageMin?minAge=${min}`,
    {
        method : 'GET',
        mode : 'cors',
        headers:{'Content-Type':'application/json'}
    }
    ).then(response => {
        if(response.ok){
            return(response.json())
        }else{
            throw new Error('cannot find the min Age');
        }
    }).then(data => {
        console.log(data);
    })
}

function filterDateRange(){
    let dateStart = document.getElementById('startDateInput').value;
    let dateEnd = document.getElementById('endDateInput').value;

    fetch(`${url}/users/dateRange?startDate=${dateStart}&endDate=${dateEnd}`,
    {
        method : 'GET',
        mode : 'cors',
        headers:{'Content-Type':'application/json'}
    }
    ).then(response => {
        if(response.ok){
            return response.json()
        }else{
            throw new error('cannot fetch data of date range')
        }
    }).then(data => {
        console.log(data);
    })
}

function saveData(){
    fetch(`${url}/save`,{
        method:'POST',
        mode:'cors',
    })
}