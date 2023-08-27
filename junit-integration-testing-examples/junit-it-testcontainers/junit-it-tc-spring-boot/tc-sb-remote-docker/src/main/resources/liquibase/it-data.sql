-- BUDGET for Editing Test
INSERT INTO public.budget (id, name)
VALUES (1, 'Budget for Edit Test');

-- BUDGET for Deletion Test
INSERT INTO public.budget (id, name)
VALUES (2, 'Budget for Delete Test');

-- BUDGET for Statement Addition Test with No Initial Statements
INSERT INTO public.budget (id, name)
VALUES (3, 'Budget for Statement Addition (No Initial Statements)');

-- BUDGET for Statement Addition Test with Multiple Initial Statements
INSERT INTO public.budget (id, name)
VALUES (4, 'Budget for Statement Addition (Multiple Initial Statements)');

-- Initial statements for 'Budget for Statement Addition (Multiple Initial Statements)'
INSERT INTO public.statement (id, created_date, updated_date, description, amount, currency, type, date, category, budget_id)
VALUES (1, '2023-08-06 00:21:46.000000', '2023-08-06 00:21:48.000000', 'Test Expense Aug 2023', 100, 'EUR', 'EXPENSE', '2023-08-06 00:22:11.000000', null, 4),
       (2, '2023-09-06 00:21:46.000000', '2023-09-06 00:21:48.000000', 'Test Income Sep 2023', 150, 'EUR', 'INCOME', '2023-09-06 00:22:11.000000', null, 4);

-- BUDGET for Single Statement Removal Test
INSERT INTO public.budget (id, name)
VALUES (5, 'Budget for Single Statement Remove Test');

-- Statement for 'Budget for Single Statement Remove Test'
INSERT INTO public.statement (id, created_date, updated_date, description, amount, currency, type, date, category, budget_id)
VALUES (3, '2023-10-06 00:21:46.000000', '2023-10-06 00:21:48.000000', 'Test Expense Oct 2023', 250, 'EUR', 'EXPENSE', '2023-10-06 00:22:11.000000', null, 5);

-- BUDGET for Multiple Statements Removal Test
INSERT INTO public.budget (id, name)
VALUES (6, 'Budget for Multiple Statements Remove Test');

-- Statements for 'Budget for Multiple Statements Remove Test'
INSERT INTO public.statement (id, created_date, updated_date, description, amount, currency, type, date, category, budget_id)
VALUES (4, '2023-11-06 00:21:46.000000', '2023-11-06 00:21:48.000000', 'Test Expense Nov 2023', 300, 'EUR', 'EXPENSE', '2023-11-06 00:22:11.000000', null, 6),
       (5, '2023-11-07 00:21:46.000000', '2023-11-07 00:21:48.000000', 'Test Income Nov 2023', 350, 'EUR', 'INCOME', '2023-11-07 00:22:11.000000', null, 6);

-- Reset sequences
SELECT setval('budget_id_seq', (SELECT MAX(id) FROM budget));
SELECT setval('statement_id_seq', (SELECT MAX(id) FROM statement));
