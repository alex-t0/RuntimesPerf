class SquareRoot {
    private num: number;
    private precision: number;
    
    constructor(N: number, precision?: number) {
        precision = precision || 0.00000001;

        this.num = N;
        this.precision = precision;
    }

    public CalculateEstimation():number {
        let countDigits:number = 0;
        let copyForCountDigits = this.num;

        let divisionDigits:number[] = [];

        while (copyForCountDigits > 0){
            divisionDigits.unshift(copyForCountDigits);
            copyForCountDigits /= 10;
            countDigits++;
        }

        if (divisionDigits.length == 0) return 0;

        let rootBase:number = divisionDigits[0];
        let exponent:number = countDigits - 1;

        if (countDigits % 2 == 0){
            rootBase = divisionDigits[1];
            exponent = countDigits - 2;
        }

        return rootBase < 10 ? 2 * Math.pow(10, exponent / 2) : 6 * Math.pow(10, exponent / 2);
    }

    public sqrt():number {
        if (this.num == 0) return 0;

        let currentPrecision = 1;
        let estimation:number = this.CalculateEstimation();

        while(currentPrecision > this.precision){
            estimation = (this.num/estimation + estimation)/2;
            currentPrecision = Math.abs(estimation * estimation - this.num);
        }

        return estimation;
    }
}

let N = 5000000;

let results:number[] = [];

let start = new Date();

for(let i=0; i<N;i++){
    results.push(new SquareRoot(i).sqrt());
}

let end = new Date();

console.log("Results[4250250] = " + results[4250250]);
console.log("Total elapsed " + (end.getTime() - start.getTime()) + " ms");

/*
results.forEach(function(val: number, ind: number){
    console.log(ind + ". " + val);
})*/
