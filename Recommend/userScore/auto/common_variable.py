import pandas as pd
import seaborn as sns
import requests
import json
import numpy as np
import base64
import time
from env import settings

GITHUB_API_TOKEN = settings.GITHUB_API_TOKEN
SC_TOKEN = settings.SC_TOKEN

MAX_PER_PAGE = 100  # 용량을 줄이기 위해 테스톨 30, 실제로는 100
MIN_CNT_FILE = 3

GH_API = 'https://api.github.com'

headers = {
    'Authorization': 'token %s' % (GITHUB_API_TOKEN),
}

GRAPH_KEYWORD_TREE = {'express': ['express', 'port'], 'socket.io': ['socket', 'on'], 'axios': ['axios', 'response'], \
                      'react': ['react', 'render'], 'angularjs': ['angular', 'controller'], 'react native': \
                          ['react-native', 'view'], 'electron': ['electron', 'app'], 'vue.js': ['vue', 'app'], 'jquery': \
                          ['jquery', 'ready'], 'next.js': ['next', 'router'], 'svelte': ['svelte', 'app'], \
                      'flask': ['flask', 'route'], 'django': ['from django', 'view'],
                      'pandas': ['import pandas', 'dataframe'], \
                      'tensorflow': ['import tensorflow', 'model'], 'scikit-learn': ['sklearn', 'predict'], \
                      'apache kafka': ['kafka', 'connect'], 'pytorch': ['import torch', 'loss'],
                      'opencv': ['cv', 'image'], \
                      'opengl': ['gl', 'display'], 'keras': ['keras', 'model'], 'apache spark': ['context', 'spark'],
                      'qt': \
                          ['<Q', 'qapplication'], '.net': ['.net', 'net'], 'blazor': ['blazor', 'net'],
                      'cuda': ['cuda', 'cudamalloc'], \
                      'laravel': ['Illuminate', 'provider'], 'ruby on rails': ['ApplicationController', 'end'],
                      'springboot': \
                          ['import org.springframework', 'springboot'], 'angular': ['angular', 'controller']}

GRAPH_STACK_TREE = {
    'javascript': ['express', 'socket.io', 'axios', 'react', 'angularjs', 'react native', 'electron', 'vue.js', \
                   'jquery', 'next.js', 'svelte', 'opengl', 'opencv'], 'html': ['electron'],
    'python': ['flask', 'django', 'pandas', \
               'tensorflow', 'scikit-learn', 'apache kafka', 'pytorch', 'opencv', 'opengl', 'keras', 'apache spark'], \
    'typescript': ['react', 'angular', 'react native'], 'java': ['springboot', 'apache kafka', 'opengl', 'opencv', \
                                                                 'apache spark'],
    'c#': ['blazor', '.net', 'opengl', 'opencv'], 'c++': ['cuda', 'apache kafka', 'opengl', \
                                                          'opencv', 'qt'], 'c': ['cuda', 'apache kafka', 'opengl'],
    'php': ['laravel'], 'css': [], \
    'go': ['apache kafka'], 'rust': [], 'kotlin': [], 'ruby': ['ruby on rails'], 'lua': [], 'dart': [], 'swift': [], \
    'r': ['apache spark'], 'node.js': ['express', 'socket.io', 'axios', 'electron'], \
    'flutter': [], '.net': ['blazor'], 'rabbitmq': []}

GRAPH_LANGUAGE = ['javascript', 'html', 'css', 'python', 'typescript', 'java', 'c#', 'c++', 'c', 'php', 'go', 'rust',
                  'kotlin' \
    , 'ruby', 'lua', 'dart', 'swift', 'r']

GRAPH_LANGUAGE_S = ['python', 'typescript', 'java', 'c#', 'c++', 'c', 'php']
''' #혹시 쓰일지도 모를, 대문자용 리스트
GRAPH_LANGUAGE = ['JavaScript', 'HTML', 'CSS', 'Python', 'TypeScript', 'Java', 'C#', 'C++', 'C', 'PHP', 'Go', 'Rust', 'Kotlin'\
               , 'Ruby', 'Lua', 'Dart',  'Swift', 'R']
'''
list_language_extension = [['js'], ['html'], ['css'], ['py'], ['ts', 'tsx'], ['java', 'class', 'jsp'], ['cs'],
                           ['cc', 'cpp', 'h', 'mm'] \
    , ['c', 'h'], ['php'], ['go'], ['rs'], ['kt'], ['rb', 'erb'], ['lua'], ['dart'], ['s'], ['swift'], ['r'], ['vb']]
