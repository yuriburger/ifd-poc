export class Import {
  public id: number;
  public name: string;
  public reason: string;

  constructor(id: number, name: string, reason: string) {
    this.id = id;
    this.name = name;
    this.reason = reason;
  }
}
