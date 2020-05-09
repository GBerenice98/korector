import { Session } from './session';
import { User } from './user';

export class Project {

  id: number;
  name: string;
  description: string;
  url: string;
  note: number;
  dateDepot: string;
  session: Array<Session>;
  user : User;
  sonarResults : Map<String,String>;
  constructor(name: string) {
    this.name = name;
    this.id = null;
    this.description= '';
    this.url= "";
    this.note= 0;
    this.dateDepot= null;
    this.session= [];
    this.user=null;
    this.sonarResults=null;

  }



}
