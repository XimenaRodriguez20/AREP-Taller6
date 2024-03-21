function loadGetMsg() {
  let msg = document.getElementById("msg").value;
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      createTableFromData(JSON.parse(this.responseText));
    }
  };
  xhttp.open("GET", "/apiclient?msg=" + msg, true);
  xhttp.send();
}

function createTableFromData(data) {
  const table = document.getElementById('logsTable');
  table.innerHTML = '';
  data.forEach(row => {
    const tr = document.createElement('tr');
    Object.values(row).forEach(col => {
      const td = document.createElement('td');
      td.textContent = col;
      tr.appendChild(td);
    });
    table.appendChild(tr);
  });
}