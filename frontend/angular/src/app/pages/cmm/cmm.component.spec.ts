import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CmmComponent } from './cmm.component';

describe('CmmComponent', () => {
  let component: CmmComponent;
  let fixture: ComponentFixture<CmmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CmmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CmmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
