import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestsubmissionComponent } from './testsubmission.component';

describe('TestsubmissionComponent', () => {
  let component: TestsubmissionComponent;
  let fixture: ComponentFixture<TestsubmissionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestsubmissionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestsubmissionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
