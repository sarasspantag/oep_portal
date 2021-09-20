import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportsFacultyComponent } from './reports-faculty.component';

describe('ReportsFacultyComponent', () => {
  let component: ReportsFacultyComponent;
  let fixture: ComponentFixture<ReportsFacultyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReportsFacultyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportsFacultyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
