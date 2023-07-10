const COLS = 8
const ROWS = 8


function main() {

    var board = document.getElementById('board');

    for (var i = 0; i < ROWS; i++) {
        var newRow = document.createElement('div');
        newRow.classList.add('row');

        for (var j = 0; j < COLS; j++) {
            var newCol = document.createElement('div');
            newCol.classList.add('square');
            var colorClass = ((i % 2) + j) % 2 == 0 ? 'black' : 'white';
            newCol.classList.add(colorClass);
            newCol.textContent = 'Q';

            newRow.appendChild(newCol)
        }

        board.appendChild(newRow);
    }

}


main()