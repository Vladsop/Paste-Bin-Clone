window.onload = function listPastes() {
    getData()
        .then(function(response) {
            for (let i = 0; i < response.length; ++i) {
                let id = response[i].id;
                let user = response[i].user;
                let title = response[i].title;
                let text = response[i].text;
                let dateArray = response[i].datecreated;
                dateArray[1] -= 1; //modified month index offset by 1, months in javascript start from 0;
                let datecreated = new Date(...dateArray).toString('YYYY-MM-dd').split(' ').slice(0, 5).join(' ');
                const div = document.getElementById('parentDiv');
                div.insertAdjacentHTML('afterbegin', '<a id="' + id + '" class="list-group-item list-group-item-action" href="updatePaste.html?' + id + '">' +
                    '<div class = "d-flex w-100 justify-content-between">' +
                    '<h5 class = "mb-1" >' + title + '</h5>' +
                    '</div>' +
                    '<p class = "mb-1" >' + text + '</p>' +
                    '<div style= "margin-top:10px; color:gray; font-size:13px;">' +
                    '<object style="text">' + 'posted by ' + '<a href="listPastesByUser.html?' + user + '"><button type="button" class="btn btn-outline-secondary btn-sm" style="font-size:12px;"><i class="far fa-user"></i> ' + user + '</button></a> on ' + datecreated + '</object>' +
                    '</div></a>')
            }
        })
}

function createPaste() {
    getData()
        .then(function(response) {
            const lastObject = response.length - 1;
            let id = response[lastObject].id;
            let user = response[lastObject].user;
            let title = response[lastObject].title;
            let text = response[lastObject].text;
            let dateArray = response[lastObject].datecreated;
            dateArray[1] -= 1; //modified month index offset by 1, months in javascript start from 0;
            let datecreated = new Date(...dateArray).toString('YYYY-MM-dd').split(' ').slice(0, 5).join(' ');
            const div = document.getElementById('parentDiv');
            div.insertAdjacentHTML('afterbegin', '<a id="' + id + '" class="list-group-item list-group-item-action" href="updatePaste.html?' + id + '">' +
                '<div class = "d-flex w-100 justify-content-between">' +
                '<h5 class = "mb-1" >' + title + '</h5>' +
                '</div>' +
                '<p class = "mb-1" >' + text + '</p>' +
                '<div style= "margin-top:10px; color:gray; font-size:13px;">' +
                '<object>' + 'posted by ' + '<a href="listPastesByUser.html?' + user + '"><button type="button" class="btn btn-outline-secondary btn-sm" style="font-size:12px;"><i class="far fa-user"></i> ' + user + '</button></a> on ' + datecreated + '</object>' +
                '</div></a>')
        })
}


const getData = () => {
    return axios.get('http://localhost:8080/paste/list/pastes').then(response => response.data);
}

const postData = () => {
    let user = document.getElementById('user').value;
    let title = document.getElementById('title').value;
    let text = document.getElementById('text').value;
    if (user === "") {
        document.getElementById('user').placeholder = "Username should not be empty. Please write your username!";
    } else {
        axios.post('http://localhost:8080/paste/create', {
            user: user,
            title: title,
            text: text
        }).then(response => {
            createPaste();
        }).catch((error) => {
            document.getElementById('title').value = "";
            document.getElementById('title').placeholder = "Title is taken. Please write a different title!";
        })
    }
}
