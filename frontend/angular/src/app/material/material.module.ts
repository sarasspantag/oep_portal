import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule , MatToolbarModule ,MatAutocompleteModule,MatBadgeModule,MatBottomSheetModule,MatButtonToggleModule,
  MatCardModule,MatCheckboxModule,MatChipsModule,MatDatepickerModule,MatDialogModule,MatDividerModule,
  MatExpansionModule,MatGridListModule,MatIconModule,MatInputModule,MatListModule,MatMenuModule,MatNativeDateModule,
  MatPaginatorModule,MatProgressBarModule,MatProgressSpinnerModule,MatRadioModule,MatRippleModule,MatSelectModule,
  MatSidenavModule,MatSliderModule,MatSlideToggleModule,MatSnackBarModule,MatSortModule,MatStepperModule,
  MatTableModule,MatTabsModule,MatTooltipModule,MatTreeModule } from '@angular/material';
  import { AngularSvgIconModule } from 'angular-svg-icon';  
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from '@angular/forms';
import { CarouselModule} from 'ngx-owl-carousel-o';
import { MatCarouselModule } from '@ngmodule/material-carousel';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { MatMomentDateModule } from '@angular/material-moment-adapter';

const Material=[FlexLayoutModule,MatButtonModule , MatToolbarModule ,MatAutocompleteModule,MatBadgeModule,MatBottomSheetModule,MatButtonToggleModule,
  MatCardModule,MatCheckboxModule,MatChipsModule,MatDatepickerModule,MatDialogModule,MatDividerModule,
  MatExpansionModule,MatGridListModule,MatIconModule,MatInputModule,MatListModule,MatMenuModule,MatNativeDateModule,
  MatPaginatorModule,MatProgressBarModule,MatProgressSpinnerModule,MatRadioModule,MatRippleModule,MatSelectModule,
  MatSidenavModule,MatSliderModule,MatSlideToggleModule,MatSnackBarModule,MatSortModule,MatStepperModule,
  MatTableModule,MatTabsModule,MatTooltipModule,MatTreeModule,AngularSvgIconModule,HttpClientModule ,FormsModule,
  CarouselModule,MatCarouselModule,CKEditorModule,MatMomentDateModule ]

@NgModule({
  imports: [
    Material,
    
  ],
  exports: [
    Material
  ],
  declarations: [
    
  ]
})
export class MaterialModule { }
