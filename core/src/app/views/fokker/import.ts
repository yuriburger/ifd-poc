export class Import {
  public id: number;
  public name: string;
  public reason: string;
  public status: string;

  constructor(id: number, name: string, reason: string, status: string) {
    this.id = id;
    this.name = name;
    this.reason = reason;
    this.status = status;
  }
}
