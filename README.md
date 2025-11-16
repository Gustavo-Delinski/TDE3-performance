# TDE3-performance

Integrantes do grupo: Alexandre Andrioli Tucci, João Victor Saboya Ribeiro de Carvalho, Gustavo Delinski Tavares

Link para o video: https://youtu.be/EjWZiKFLzHg

Relatório — Dinâmica do Problema, Impasse e Condições de Coffman

No Jantar dos Filósofos, cinco processos competem por garfos compartilhados para poder comer. Cada filósofo alterna entre pensar e tentar adquirir dois garfos. No protocolo ingênuo, todos podem pegar um garfo e esperar indefinidamente pelo segundo, gerando impasse. Esse deadlock surge porque o sistema satisfaz as quatro condições de Coffman: exclusão mútua (cada garfo só pode ser usado por um filósofo), manter-e-esperar (os filósofos seguram um garfo enquanto aguardam outro), não preempção (um garfo não pode ser retirado à força) e espera circular (cada filósofo espera por um recurso segurado por outro).

A solução proposta aplica uma hierarquia de recursos, impondo uma ordem fixa na aquisição dos garfos. Com isso, cada filósofo sempre tenta pegar primeiro o garfo de menor índice e depois o de maior. Essa estratégia elimina a condição de espera circular, quebrando o ciclo entre os processos e tornando o deadlock impossível, enquanto as demais condições permanecem.
