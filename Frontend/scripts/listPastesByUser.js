var queryId = location.search.substring(1);

const getDataByUser = async () => {
    try {
        return await axios.get('http://localhost:8080/paste/list/user/' + queryId).then(response => response.data);
    } catch (err) {
        console.error(err);
    }
}

window.onload = function displayPastesList() {
    getDataByUser()
        .then(function(response) {
            const div = document.getElementById('parentDiv');
            if (response.length <= 0){
                div.insertAdjacentHTML('afterbegin', '<object>' + 'There are no more pastes from user <button type="button" class="btn btn-outline-secondary btn-sm" style="font-size:12px;"><i class="far fa-user"></i> ' + queryId +'</button></object>');
            }
            for (let i = 0; i < response.length; ++i) {
                let id = response[i].id;
                let user = response[i].user;
                let title = response[i].title;
                let text = response[i].text;
                let dateArray = response[i].datecreated;
                dateArray[1] -= 1; //modified month index offset by 1, months in javascript start from 0;
                let datecreated = new Date(...dateArray).toString('YYYY-MM-dd').split(' ').slice(0, 5).join(' ');
                document.getElementById('userPastes').innerHTML = "Pastes from user: " + user;
                div.insertAdjacentHTML('afterbegin', '<a id="' + id + '" class="list-group-item list-group-item-action" href="updatePaste.html?' + id + '">' +
                    '<div class = "d-flex w-100 justify-content-between">' +
                    '<h5 class = "mb-1" >' + title + '</h5>' +
                    '</div>' +
                    '<p class = "mb-1" >' + text + '</p>' +
                    '<div style= "margin-top:10px; color:gray; font-size:13px;">' +
                    '<object style="text">'+'posted by ' + '<a href="listPastesByUser.html?'+ user +'"><button type="button" class="btn btn-outline-secondary btn-sm" style="font-size:12px;"><i class="far fa-user"></i> ' + user +'</button></a> on ' + datecreated + '</object>'+
                    '</div></a>');
            }
        })
}
