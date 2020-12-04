using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KillPlayer : MonoBehaviour 
{
    [SerializeField]Transform spawnPoint;

    void OnCollisionEnter2D(Collision2D collision2D)
    {
        if (collision2D.transform.CompareTag("Player"))
        {
            collision2D.transform.position = spawnPoint.position;
        }
    }
}
