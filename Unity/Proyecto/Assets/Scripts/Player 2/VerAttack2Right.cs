﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class VerAttack2Right : MonoBehaviour
{
    public static bool colli;
    
    private void OnTriggerEnter2D(Collider2D collision){
        if (collision.CompareTag("Player")){
            colli=true;
            
        }
        else {
            colli=false;
        }
    }
    private void OnTriggerExit2D(Collider2D collision){
        colli=false;     
    }
}
