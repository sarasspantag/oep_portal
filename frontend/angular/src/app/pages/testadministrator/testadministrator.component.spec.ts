import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestadministratorComponent } from './testadministrator.component';

describe('TestadministratorComponent', () => {
  let component: TestadministratorComponent;
  let fixture: ComponentFixture<TestadministratorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestadministratorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestadministratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
