INSERT INTO public.amministratore (id, password, ruolo, username)
VALUES (1, '$2a$10$wrqP0kMwSODjC.LhKnsnLua7CYfVeYY3aCc3I2fUGUbOD/s2s0KcO', 'AMMINISTRATORE', 'admin');

INSERT INTO public.curatore (matricola, cognome, data_di_nascita, luogo_di_nascita, nome, email, numero_di_telefono)
VALUES ('cur1', 'Pinzarrone', '1999-09-19', 'Roma', 'Flavio', 'fla.pinzarrone@stud.uniroma3.it', '+39339383273');
INSERT INTO public.curatore (matricola, cognome, data_di_nascita, luogo_di_nascita, nome, email, numero_di_telefono)
VALUES ('cur2', 'Rocco', '1997-02-08', 'Dortmund', 'Luca', 'luc.rocco@stud.uniroma3.it', '+39383928372');

INSERT INTO public.artista (id, cognome, data_di_nascita, luogo_di_nascita, nome, biografia, data_di_morte, foto,
                            luogo_di_morte, nazionalita)
VALUES (1, 'Munch', '1863-12-12', 'Løten', 'Edvard', 'Munch nasce nel 1863 e muore nel 1944. Egli é il più importante pittore norvegese, uno dei due precursori della pittura espressionista. La sua vita fu segnata da fatti tragici: quando aveva solo cinque anni la madre muore di tubercolosi e poco dopo perse anche la sorella nello stesso modo. Il padre é un uomo ossessionato dalla religione, e muore anche lui quando l''artista aveva solo diciotto anni. Munch ha segni di disagi psichici piuttosto importanti, era un uomo molto affascinante e piaceva molto alle donne.

 
Ha una vita contrassegnata da viaggi. Ha un concetto di uomo/donna molto particolare: vede le donne come vampiri, capaci di risucchiare il meglio di un uomo.',
        '1944-01-23',
        'http://www.psicologo-genova-busca.it/wp-content/uploads/2018/04/Munch_Mordeglia_Benzi_Psicologo_Genova_Busca-300x226.jpeg',
        'Oslo', 'Norvegia');
INSERT INTO public.artista (id, cognome, data_di_nascita, luogo_di_nascita, nome, biografia, data_di_morte, foto,
                            luogo_di_morte, nazionalita)
VALUES (2, 'Picasso', '1881-10-25', 'Malaga', 'Pablo', 'Pablo Picasso nasce nel 1881 a Malaga, in Spagna. Suo padre è insegnante nella locale scuola d’arte e lo avvia molto presto all’apprendistato artistico. A soli quattordici anni viene ammesso all’Accademia di Belle Arti di Barcellona, e due anni dopo si trasferisce all’Accademia di Madrid.

Dopo un ritorno a Barcellona, va a Parigi nel 1900: vi farà ritorno più volte, fino a stabilirvisi definitivamente. Dal 1901 lo stile di Picasso inizia a mostrare dei tratti originali.',
        '1973-04-08',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Pablo_picasso_1.jpg/800px-Pablo_picasso_1.jpg',
        'Mougsin', 'Spagna');
INSERT INTO public.artista (id, cognome, data_di_nascita, luogo_di_nascita, nome, biografia, data_di_morte, foto,
                            luogo_di_morte, nazionalita)
VALUES (3, 'Monet', '1840-11-14', 'Parigi', 'Oscar-Claude',
        'Capofila del movimento impressionista, portò al massimo livello lo studio degli effetti mutevoli della luce naturale. Mostrò prestissimo il suo talento artistico, cominciando fin da ragazzo a disegnare caricature, per poi seguire l''insegnamento di Eugène Boudin che lo incoraggiò a dipingere all''aperto. Nel 1859, da Le Havre, dov''era cresciuto, si trasferì a Parigi, iniziando a frequentare Edouard Manet e altri pittori che in seguito avrebbero formato il gruppo impressionista, come Camille Pissarro, Pierre-Auguste Renoir e Alfred Sisley.',
        '1926-12-05',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Autoportret_Claude_Monet.jpg/475px-Autoportret_Claude_Monet.jpg',
        'Giverny', 'Francia');
INSERT INTO public.artista (id, cognome, data_di_nascita, luogo_di_nascita, nome, biografia, data_di_morte, foto,
                            luogo_di_morte, nazionalita)
VALUES (4, 'van Gogh', '1853-03-30', 'Zundert', 'Vincent Willem', 'Geniale, folle, visionario. Vincent van Gogh può essere considerato il pioniere dell’arte contemporanea, padre dell’Espressionismo ed emblema dell’artista tormentato. Se la sua vita fosse stata un romanzo, sarebbe stato uno di quei romanzi inverosimili, esagerati, troppo carichi di colpi di scena per sembrare veri, ma la vita di van Gogh non è frutto della mente fertile di uno scrittore. Quello che vi racconterò in questi due minuti è avvenuto davvero.

', '1890-07-29',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/VanGogh_1887_Selbstbildnis.jpg/220px-VanGogh_1887_Selbstbildnis.jpg',
        'Auvers-sur-Oise', 'Olanda');
INSERT INTO public.artista (id, cognome, data_di_nascita, luogo_di_nascita, nome, biografia, data_di_morte, foto,
                            luogo_di_morte, nazionalita)
VALUES (5, 'Duchamp', '1887-07-28', 'Blainville-Crevon', 'Marcel',
        'Marcel Duchamp (1887-1968), pittore francese, è considerato uno dei maggiori rappresentanti del Dadaismo, anche se non ha mai accettato l’appartenenza a questo gruppo. È stato uno dei più grandi artisti del Novecento, ha costruito un nuovo prototipo di artista da intendersi come intellettuale sempre pronto a proporsi in maniera inaspettata, anche solo per il piacere di essere diverso dal normale.',
        '1968-10-02',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Marcel_Duchamp_01.jpg/220px-Marcel_Duchamp_01.jpg',
        'Neuilly-sur-Seine', 'Francia');

INSERT INTO public.opera (id, anno_realizzazione, descrizione, foto, titolo, autore_id)
VALUES (30, E '\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C0000787077050B0000076578', '',
        'https://res.cloudinary.com/pickee-server/image/upload/v1622998373/preqyjyefs328ab6qbv9.jpg', 'L''urlo', 1);
INSERT INTO public.opera (id, anno_realizzazione, descrizione, foto, titolo, autore_id)
VALUES (33, E '\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C0000787077050B0000078C78', '',
        'https://res.cloudinary.com/pickee-server/image/upload/v1622998912/si9jd3hcjecbamtvkwao.jpg', 'Il Sogno', 2);
INSERT INTO public.opera (id, anno_realizzazione, descrizione, foto, titolo, autore_id)
VALUES (34, E '\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C0000787077050B0000076178', '',
        'https://res.cloudinary.com/pickee-server/image/upload/v1622999157/w73rfzracqzpnvcs3fjr.jpg', 'Autoritratto',
        4);
INSERT INTO public.opera (id, anno_realizzazione, descrizione, foto, titolo, autore_id)
VALUES (35, E '\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C0000787077050B0000074C78', '',
        'https://res.cloudinary.com/pickee-server/image/upload/v1622999234/zyga3tyfyfesa8x51n8q.jpg',
        'Suzanne Hoschedé', 3);
INSERT INTO public.opera (id, anno_realizzazione, descrizione, foto, titolo, autore_id)
VALUES (31, E '\\xACED00057372000D6A6176612E74696D652E536572955D84BA1B2248B20C0000787077050B0000077F78', '',
        'https://live.staticflickr.com/3070/2754329927_22de4fb39b_b.jpg', 'L.H.O.O.Q.', 5);

INSERT INTO public.collezione (id, descrizione, nome, curatore_matricola)
VALUES (36, 'Opere del 1800', 'Il 1800', 'cur1');
INSERT INTO public.collezione (id, descrizione, nome, curatore_matricola)
VALUES (37, 'Opere del 1900', 'Il 1900', 'cur2');

INSERT INTO public.collezione_opere (collezioni_id, opere_id)
VALUES (36, 34);
INSERT INTO public.collezione_opere (collezioni_id, opere_id)
VALUES (36, 35);
INSERT INTO public.collezione_opere (collezioni_id, opere_id)
VALUES (37, 30);
INSERT INTO public.collezione_opere (collezioni_id, opere_id)
VALUES (37, 31);
INSERT INTO public.collezione_opere (collezioni_id, opere_id)
VALUES (37, 33);

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
