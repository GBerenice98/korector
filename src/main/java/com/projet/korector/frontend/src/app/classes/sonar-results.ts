export class SonarResults {
    id : Number;
    bugs : String;
    vuls: String;
    debt: String;
    smells: String;
    coverage:String;
     dups: String;
    dups_block : String;
    date : String;
    note_finale: Number;
    projectId: Number;
    sessionId: Number;

    constructor() {
        this.id = null;
        this.bugs= '';
        this.vuls='';
        this.debt='';
        this.smells= '';
        this.dups='';
        this.dups_block='';
        this.date = '';
        this.projectId = null;
        this.sessionId= null;
        this.note_finale= null;

      }

}

