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

const TEST_BOARD = [
    {
        "piece": "WC",
        "row": 0,
        "col": 0
    },
    {
        "piece": "WN",
        "row": 0,
        "col": 1
    },
    {
        "piece": "WB",
        "row": 0,
        "col": 2
    },
    {
        "piece": "WQ",
        "row": 0,
        "col": 3
    },
    {
        "piece": "WK",
        "row": 4,
        "col": 4
    },
    {
        "piece": "BB",
        "row": 2,
        "col": 6
    },
]

function populateBoard(boardPositions) {
    boardPositions.forEach(boardPosition => {
        var image = document.createElement('img')
        image.setAttribute('src', IMAGE_MAPPINGS[boardPosition.piece]);
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

function main() {
    buildBoard();
    populateBoard(TEST_BOARD)
//    clearBoard()
}

main()