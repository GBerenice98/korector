export class SectionName {
        id: number;
        name: string;
        startYear: number;
        endYear: number;
        teachers: any;
        students: any;
    constructor(name: string, startYear: number, endYear: number, teacher: any, students: any) {
            this.name = name;
            this.startYear = startYear;
            this.endYear = endYear;
            this.students = students;
            this.teachers = teacher;

          }

}
