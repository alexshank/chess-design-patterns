const COLS = 8
const ROWS = 8

IMAGE_MAPPINGS = {
    "WK": "images/white_king.svg",
    "WQ": "images/white_queen.svg",
    "WB": "images/white_bishop.svg",
    "WN": "images/white_knight.svg",
    "WC": "images/white_castle.svg",
    "WP": "images/white_pawn.svg",
    "BK": "images/black_king.svg",
    "BQ": "images/black_queen.svg",
    "BB": "images/black_bishop.svg",
    "BN": "images/black_knight.svg",
    "BC": "images/black_castle.svg",
    "BP": "images/black_pawn.svg",
}

function getNewGame() {
    fetch('http://localhost:8080/api/new')
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Request failed with status code ' + response.status);
            }
        })
        .then(function(data) {
            console.log(data);
            clearBoard();
            populateBoard(data.pieces);
        })
        .catch(function(error) {
            console.error('Request failed:', error);
        });
}

function populateBoard(boardPositions) {
    boardPositions.forEach(boardPosition => {
        var image = document.createElement('img')
        image.setAttribute('src', IMAGE_MAPPINGS[boardPosition.label]);
        square = document.getElementById(`${boardPosition.row}${boardPosition.col}`);
        square.appendChild(image);
    })
}

function clearBoard() {
    for (var row = 0; row < ROWS; row++) {
        for (var col = 0; col < COLS; col++) {
            var square = document.getElementById(`${row}${col}`);
            if (square.firstChild) {
                square.removeChild(square.lastChild);
            }
        }
    }
}

function buildBoard() {
    var board = document.getElementById('board');
    for (var row = 0; row < ROWS; row++) {
        var newRow = document.createElement('div');
        newRow.classList.add('row');
        for (var col = 0; col < COLS; col++) {
            var newCol = document.createElement('div');
            newCol.classList.add('square');
            var colorClass = ((row % 2) + col) % 2 == 1 ? 'black' : 'white';
            newCol.classList.add(colorClass);
            newCol.setAttribute('id', `${ROWS - 1 - row}${col}`)

            newCol.addEventListener('click', function (event) {
                var targetElement = event.target.nodeName === "DIV"
                    ? event.target
                    : event.target.parentElement;
                console.log(targetElement);
            });

            newRow.appendChild(newCol)
        }
        board.appendChild(newRow);
    }
}

function setupNewGameButton() {
    var chessButton = document.getElementById("chess-button");
    chessButton.addEventListener("click", getNewGame);
}

function main() {
    setupNewGameButton();
    buildBoard();
}

main()