
# coding: utf-8

# In[3]:


import json
def dataout(path):
    with open(path) as f:
        data = json.load(f)
    print(data)

