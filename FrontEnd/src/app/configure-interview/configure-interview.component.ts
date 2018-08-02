import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { StatsService } from '../stats.service';

@Component({
  selector: 'app-configure-interview',
  templateUrl: './configure-interview.component.html',
  styleUrls: ['./configure-interview.component.css']
})
export class ConfigureInterviewComponent implements OnInit {
  isValid:boolean = true;
  canReset:boolean = true;
  successSubmit:boolean = false;
  systemForm: FormGroup;
  constructor(private fb: FormBuilder,
    private router: Router,
    private statsService: StatsService) { }

  ngOnInit() {
    this.systemForm = this.fb.group({
      noOfRounds: ['', [Validators.required,Validators.min(1)]],
      minimumQuestions: [['', Validators.required,Validators.min(1)]],
      threshold: [['', Validators.required,Validators.min(1)]]
    });


  }

  handleSubmit(event) {
    this.canReset=true;
    this.successSubmit = false;
    event.preventDefault();
    if (this.systemForm.valid) {
      this.isValid = true;
      this.statsService.loadStats()
        .subscribe(stats => {
          console.log(stats[0]);
          if (stats[0] > 0) {
            this.canReset=false;
            console.log('Cant Configure');
          }
          else {
            this.statsService.setAttributes(this.systemForm.value).subscribe(response => {
              console.log(response);
              this.successSubmit = true;
            });
          }
          this.systemForm.reset();
        });

    }else{
      this.isValid=false;
    }

  }



}
