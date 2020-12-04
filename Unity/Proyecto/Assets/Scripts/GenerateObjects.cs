using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GenerateObjects : MonoBehaviour
{   
    public GameObject boxPrefab1;
    public GameObject boxPrefab2;
    public GameObject boxPrefab3;
    public float respawnTime = 3.0f;
    private Vector2 screenBounds;

    void Start()
    {
        screenBounds = Camera.main.ScreenToWorldPoint(new Vector3(Screen.width, Screen.height, Camera.main.transform.position.z));
        StartCoroutine(wave());
    }

    private void spawnObject(){
        float spawn = Random.Range(1,4);
        if (spawn == 1)
        {
            GameObject a = Instantiate(boxPrefab1) as GameObject;
            a.transform.position = new Vector2(Random.Range(-screenBounds.x, screenBounds.x), screenBounds.y*1.5f);
        }else if( spawn == 2)
        {
            GameObject a = Instantiate(boxPrefab2) as GameObject;
            a.transform.position = new Vector2(Random.Range(-screenBounds.x, screenBounds.x), screenBounds.y*1.5f);
        }else
        {
            GameObject a = Instantiate(boxPrefab3) as GameObject;
            a.transform.position = new Vector2(Random.Range(-screenBounds.x, screenBounds.x), screenBounds.y*1.5f);
        }
        

    }

    IEnumerator wave()
    {
        while (true)
        {
            yield return new WaitForSeconds(respawnTime);
            spawnObject();
        }
        
    }
}
