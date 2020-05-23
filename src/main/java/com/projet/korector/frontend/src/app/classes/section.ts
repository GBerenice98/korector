export class Section {
  id: number;
  name: string;
  startYear: number;
  endYear: number;

  constructor(name: string, startYear: number, endYear: number) {
    this.name = name;
    this.startYear = startYear;
    this.endYear = endYear;

  }
}
