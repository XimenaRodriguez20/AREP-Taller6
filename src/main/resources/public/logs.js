function loadGetMsg() {
    let msg = document.getElementById("msg").value;
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //createTable(JSON.parse(this.responseText));
            document.getElementById("logsTable").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "http://localhost:8080/apiclient?msg=" + msg, true);
    xhttp.send();
}