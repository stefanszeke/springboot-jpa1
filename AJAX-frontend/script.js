const studentsTable = document.querySelector("#students-table");
const studentTableId = document.querySelector("#students-table-id");

const messageBox = document.querySelector("#message");

const firstNameInput = document.querySelector("#firstName");
const lastNameInput = document.querySelector("#lastName");
const emailInput = document.querySelector("#email");

const firstNamePatch = document.querySelector("#patch-firstName");
const lastNamePatch = document.querySelector("#patch-lastName");
const emailPatch = document.querySelector("#patch-email");

const patchId = document.querySelector("#patch-id");

// Load text file with AJAX
function loadTextFile() {

    const xhttp = new XMLHttpRequest(); // Create a new XMLHttpRequest object

    xhttp.onreadystatechange = function() { // When the state of the object changes, this function is called
        if (this.readyState == 4 && this.status == 200) { // If the request is completed and the status is OK,
            document.querySelector("#text-id").innerHTML = this.responseText; // Set the text of the element with the id "text-id" to the response text
        }
    };
    xhttp.open("GET", "text.txt", true); // Open the file
    xhttp.send(); // Send the request
}

// GET request
function getStudents() {

    studentsTable.innerHTML = "";

    const getStudents = new XMLHttpRequest();
    
    getStudents.open("GET", "http://localhost:8080/api/v1/students");
    getStudents.setRequestHeader("Content-Type", "application/json");

    getStudents.onloadstart = function() {
        studentsTable.innerHTML = "Loading...";
    }

    getStudents.onload = function() {
        setTimeout(() => {
            studentsTable.innerHTML = "";
            const students = JSON.parse(getStudents.responseText);
            createStudentsTableHeader(studentsTable);
            createStudentsTableFromData(studentsTable ,students);
        }, 500)
        }

        getStudents.onerror = function() {
            studentsTable.innerHTML = "Error";
        }

    getStudents.send();
}

// GET request with parameters
function getStudentById() {

        studentTableId.innerHTML = "";
    
        const id = document.querySelector("#student-id").value;
    
        if ([id] == "") return alert("Please enter an ID number");
        if(isNaN(id)) return alert("Please enter a valid ID number");

        const getStudentById = new XMLHttpRequest();
    
        getStudentById.open("GET", `http://localhost:8080/api/v1/students/${id}`);
        getStudentById.setRequestHeader("Content-Type", "application/json");

        
        getStudentById.onreadystatechange = function() {
            
            if (this.readyState > 0 && this.readyState < 4) {
                studentTableId.innerHTML = "Loading...";
            }

            setTimeout(() => {         
                if (this.readyState == 4 && this.status == 200) {
                    studentTableId.innerHTML = "";
                    const student = JSON.parse(getStudentById.responseText);
                    createStudentsTableHeader(studentTableId);
                    createStudentsTableFromData(studentTableId ,[student]);
                } 
                if (this.readyState == 4 && this.status != 200) {
                    studentTableId.innerHTML = "Student not found";
                }
            }, 500)


        }
        
        getStudentById.send();
}


// POST request
function postStudents() {
    
        if ([firstNameInput.value, lastNameInput.value, emailInput.value].includes("")) return setMessage("Please fill out all fields");
    
        const postStudent = new XMLHttpRequest();
    
        postStudent.open("POST", "http://localhost:8080/api/v1/students");
        postStudent.setRequestHeader("Content-Type", "application/json");
    
        postStudent.onload = function() {
            if (this.status == 200) {
                resetForm();
                getStudents();
                setMessage("Student added");
            } else {
                setMessage("Student not added");
            }
        }

        const studentToSend = {
            firstName: firstNameInput.value,
            lastName: lastNameInput.value,
            email: emailInput.value
        }

        postStudent.send(JSON.stringify(studentToSend));
}

// DELETE request with parameters
function deleteStudentById() {

    const deleteStudent = new XMLHttpRequest();
    
    const id = document.querySelector("#delete-student-id").value;

    if ([id] == "") return setMessage("Please enter an ID number");
    if(isNaN(id)) return setMessage("Please enter a valid ID number");

    deleteStudent.open("DELETE", `http://localhost:8080/api/v1/students/${id}`);
    deleteStudent.setRequestHeader("Content-Type", "application/json");

    deleteStudent.onload = function() {
        if (this.status == 200) {
            setMessage("Student deleted");
            getStudents();
        } else {
            setMessage("Student not Found");
        }
    }

    deleteStudent.send();
}

// PATCH request with parameters
function patchStudentById() {

    const patchStudent = new XMLHttpRequest();

    const studentToSend = {}

    if(firstNamePatch.value.length > 0) { studentToSend.firstName = firstNamePatch.value; }
    if(lastNamePatch.value.length > 0) { studentToSend.lastName = lastNamePatch.value; }
    if(emailPatch.value.length > 0) { studentToSend.email = emailPatch.value; }

    const id = patchId.innerHTML;

    if (id == "") return setMessage("Please select an ID number");

    patchStudent.open("PATCH", `http://localhost:8080/api/v1/students/${id}`);
    patchStudent.setRequestHeader("Content-Type", "application/json");

    patchStudent.onload = function() {
        if (this.status == 200) {
            setMessage("Student updated");
            resetPatchForm();
            getStudents();
        } else {
            setMessage("Student not Found");
        }
    }


    patchStudent.send(JSON.stringify(studentToSend));
}


// readyState values:
// 0: request not initialized
// 1: server connection established
// 2: request received
// 3: processing request
// 4: request finished and response is ready

// status values:
// 200: "OK"
// 403: "Forbidden"
// 404: "Not Found"

// The readyState and status properties are always available, but the responseText and responseXML properties are only available when readyState is 4 and status is 200.

// The responseText property contains the response as a string.
// The responseXML property contains the response as an XML DOM object.

// The open() method takes three parameters:
// 1. The request type (GET, POST, PUT, DELETE, etc.)
// 2. The file name
// 3. A boolean value indicating whether the request should be asynchronous or not

// The send() method sends the request to the server. If the request is asynchronous, the send() method is executed before the response is ready.

// The setRequestHeader() method adds a label/value pair to the header to be sent. It must be added after the open() method, but before the send() method.

// The getResponseHeader() method returns the value of a specified header. It must be added after the send() method.

// onload vs onreadystatechange: 
// The onload property is a function that is called when the request is completed.
// The onreadystatechange property is a function that is called when the readyState property changes.

//
// helpers:

function createStudentsTableHeader(table) {
    const row = document.createElement("tr");
    const id = document.createElement("th");
    id.innerHTML = "ID";
    const firstName = document.createElement("th");
    firstName.innerHTML = "First Name";
    const lastName = document.createElement("th");
    lastName.innerHTML = "Last Name";
    const email = document.createElement("th");
    email.innerHTML = "Email";

    row.append(id, firstName, lastName, email);
    table.appendChild(row);
}

function createStudentsTableFromData(table, data) {
    for(let i = 0; i < data.length; i++) {
        
        const student = data[i];
        const row = document.createElement("tr");

        const id = document.createElement("td");
        id.innerHTML = student.id;
        id.classList.add("td-id");
        id.addEventListener("click", () => {
            patchId.innerHTML = student.id;
            firstNamePatch.value = student.firstName;
            lastNamePatch.value = student.lastName;
            emailPatch.value = student.email;
        });

        const firstName = document.createElement("td");
        firstName.innerHTML = student.firstName

        const lastName = document.createElement("td");
        lastName.innerHTML = student.lastName

        const email = document.createElement("td");
        email.innerHTML = student.email;

        row.append(id, firstName, lastName, email);
        table.appendChild(row);
    }
}

function resetForm() {
    firstNameInput.value = "";
    lastNameInput.value = "";
    emailInput.value= "";
}

function resetPatchForm() {
    firstNamePatch.value = "";
    lastNamePatch.value = "";
    emailPatch.value= "";
    patchId.innerHTML = "";
}

function setMessage(message) {
    messageBox.classList.add("red-message");
    messageBox.innerHTML = message;

    setTimeout(() => {
        messageBox.classList.remove("red-message");
        messageBox.innerHTML = "Hello";
    }, 3000);
    
}