var queryId = location.search.substring(1);

const getDataById = () => {
    return axios.get('http://localhost:8080/paste/list/id/' + queryId).then(response => response.data);
}

window.onload = function displayPaste() {
    getDataById()
        .then(function(response) {
            let id = response.id;
            let user = response.user;
            let title = response.title;
            let text = response.text;
            let dateArray = response.datecreated;
            dateArray[1] -= 1; //modified month index offset by 1, months in javascript start from 0;
            let datecreated = new Date(...dateArray).toString('YYYY-MM-dd').split(' ').slice(0, 5).join(' ');
            document.getElementById('pastetitle').innerHTML = "Paste title: " + title;
            document.getElementById('text').innerHTML = text;
            document.getElementById('title').placeholder = title;
            document.getElementById('user-datecreated').innerHTML = '<object>' + 'posted by ' + '<a href="listPastesByUser.html?' +
                user + '"><button type="button" class="btn btn-outline-secondary btn-sm" style="font-size:12px;"><i class="far fa-user"></i> ' +
                user + '</button></a> on ' + datecreated + '</object>';
        })
}
const updateData = () => {
    getDataById().then(function(response) {
        let user = response.user;
        let title = document.getElementById('title').value;
        let text = document.getElementById('text').value;
        axios.put('http://localhost:8080/paste/update/' + queryId + "?title=" + title + '&text=' + text).then(response => {
            location.href = 'listPastesByUser.html?' + user;
            console.log(response);
        });
    })
}

const deleteData = () => {
    getDataById().then(function(response) {
        let user = response.user;
        axios.delete('http://localhost:8080/paste/delete/' + queryId).then(response => {
            location.href = 'listPastesByUser.html?' + user;
        });
    })
}
