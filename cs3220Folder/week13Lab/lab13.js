//Write a JavaScript function that takes an array of numbers as argument and returns the largest number in the array.

array = [1, 56, 123, 87, 12, 0, 76, 23, -43, 5, -2];

console.log("values in array: " + array);
max1(array);
max2(array);
max3(array);

// a) (15pt) Implement the function using a loop statement.
function max1(array) {
    maximumValue = array[0];
    for (let i = 1; i < array.length; i++) {
        if (array[i] > maximumValue) {
            maximumValue = array[i];
        }
    }
    console.log("Maximum value from max1() method: " + maximumValue);
}

// b) (15pt) Implement the function using the forEach() method in Array.
function max2(array) {
    maximumValue = Number.MIN_VALUE;
    array.forEach(function(element) {
        if (element > maximumValue) {
            maximumValue = element;
        }
    });
    console.log("Maximum value from max2() method: " + maximumValue);
}

// c) (15pt) Implement the function using the reduce() method in Array.
function max3(array) {
    const getMax = (a, b) => Math.max(a, b);
    console.log("Maximum value from max3() method: " + array.reduce(getMax, Number.MIN_VALUE));
}