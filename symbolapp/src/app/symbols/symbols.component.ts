import { Component, OnInit } from '@angular/core';
import { Symbol } from '../symbol';
import { SYMBOLS } from '../mock-symbols';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-symbols',
  templateUrl: './symbols.component.html',
  styleUrls: ['./symbols.component.css'],  
})
export class SymbolsComponent implements OnInit {
  symbols = SYMBOLS;
  selectedSymbol?: Symbol;
 canvas?: HTMLCanvasElement;
  ctx?: CanvasRenderingContext2D | null;

  ngOnInit() {
    this.canvas = document.getElementById('canvas1') as HTMLCanvasElement;
  }

  ngAfterViewInit() {
    this.canvas = document.getElementById('canvas1') as HTMLCanvasElement;
    this.ctx = this.canvas.getContext('2d');

    this.canvas.width = 100;
    this.canvas.height = 100;
  }

  onSelect(symbol: Symbol): void {
    this.selectedSymbol = symbol;
    
    if (this.ctx != null) {
      this.ctx.save();
      this.ctx.clearRect(0, 0, this.canvas!.width, this.canvas!.height);

      // setup context
      if ((typeof (symbol.fill) != "undefined") && symbol.fill) this.ctx.fillStyle = symbol.fill!;
      if ((typeof (symbol.fillopacity) != "undefined") && symbol.fillopacity) this.ctx.globalAlpha = symbol.fillopacity!;
      if ((typeof (symbol.stroke) != "undefined") && symbol.stroke) this.ctx.strokeStyle = symbol.stroke!;
      if ((typeof (symbol.strokeopacity) != "undefined") && symbol.strokeopacity) this.ctx.globalAlpha = symbol.strokeopacity!;
      if ((typeof (symbol.strokewidth) != "undefined") && symbol.strokewidth! > 0) {
        this.ctx.lineWidth = symbol.strokewidth!;
      }
      if ((typeof (symbol.stokelinecap) != "undefined") && symbol.stokelinecap) this.ctx.lineCap = symbol.stokelinecap;
      if ((typeof (symbol.strokedasharray) != "undefined") && symbol.strokedasharray) {
        var dasharray: number[] = [];
        var dashstrings: string[] = symbol.strokedasharray!.split(/\s/);

        dashstrings.forEach(dash => {
          dasharray.push(Number(dash));
        });
        this.ctx.setLineDash(dasharray);
      }
      // setup context

      // draw symbol
      if (this.selectedSymbol.type === "Punt") this.drawPoint(this.selectedSymbol, this.canvas!, this.ctx);
      if (this.selectedSymbol.type === "Lijn") this.drawLijn(this.selectedSymbol, this.canvas!, this.ctx);
      if (this.selectedSymbol.type === "Vlak") this.drawVlak(this.selectedSymbol, this.canvas!, this.ctx);
      // draw symbol
      this.ctx.restore();
  
    } else {
      console.log('ctx is null');
    }
        
  }

  drawPoint(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    switch (symbol.welknownname) {
      case "circle":
        this.drawCircle(symbol, canvas, ctx);
        break;
      case "square":
        this.drawSquare(symbol, canvas, ctx);
        break;
      case "cross_fill":
        this.drawCross(symbol, canvas, ctx);
        break;
      case "triangle":
        this.drawTriangle(symbol, canvas, ctx);
        break;
      case "star":
        this.drawStar(symbol, canvas, ctx);
        break;
      default:
        console.log("Unknown type");
        break;
    }   
  }

  private drawCircle(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.arc(canvas.width / 2, canvas.height / 2, Math.min(canvas.width / 2, canvas.height / 2) * 0.8, 0, 2 * Math.PI);
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawSquare(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.rect(0, 0, canvas.width, canvas.height);
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawCross(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.stroke();
    ctx.closePath();
  }

  private drawTriangle(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    var path1 = new Path2D();

    ctx.beginPath();
    path1.moveTo(canvas.width / 2, 0);
    path1.lineTo(canvas.width, canvas.height);
    path1.lineTo(0, canvas.height);
    path1.lineTo(canvas.width / 2, 0);

    ctx.fill(path1);
    ctx.closePath();
  }

  /*
  see https://math.stackexchange.com/questions/3582342/coordinates-of-the-vertices-of-a-five-pointed-star
  */
 
  private drawStar(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    var radius_outside = Math.min(canvas.width / 2, canvas.height / 2);
    var radius_inside = radius_outside * 0.5;
    var path1 = new Path2D();

    var r = 0;
    var [x, y] = [0, 0];
    var [xstart, ystart] = [canvas.width / 2, canvas.height / 2];

    ctx.beginPath();
    for (let i = 0; i < 5; i++) {
      // big ring
      [x, y] = this.starCoordsOut(i, radius_outside);
      if (i == 0) {
        path1.moveTo(x + xstart, y + ystart);
      } else {
        path1.lineTo(x + xstart, y + ystart);
      }
      // small ring
      [x, y] = this.starCoordsIn(i, radius_inside);
      path1.lineTo(x + xstart, y + ystart);
    }
    [x, y] = this.starCoordsOut(0, radius_outside);
    path1.lineTo(x + xstart, y + ystart);

    ctx.fill(path1);
    ctx.closePath();
  }

  private drawLijn(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.moveTo(10, 50);
    ctx.lineTo(canvas.width - 20, 50);
    ctx.stroke();
  }

  private drawVlak(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {

    if ((typeof (symbol.stroke) != "undefined") && symbol.stroke) ctx.fillStyle = symbol.stroke!;

    ctx.beginPath();
    ctx.rect(0, 0, canvas.width, canvas.height);
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.stroke();
    ctx.fill();
  }

  private starCoordsOut(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 2 * Math.PI * index / 5 - Math.PI / 10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    return [x, y];
  }

  private starCoordsIn(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 4 * Math.PI * (index + 0.5) / 10 - Math.PI / 10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    return [x, y];
  }

}
