export class Taak {
  public id: string;
  public name: string;
  public assignee: string;

  constructor(id: string, name: string, assignee: string) {
    this.id = id;
    this.name = name;
    this.assignee = assignee;
  }
}
